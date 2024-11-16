package app.techify.service;

import app.techify.entity.Order;
import app.techify.entity.Voucher;
import app.techify.repository.OrderDetailRepository;
import app.techify.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import app.techify.dto.OrderResponse;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    public Order createOrder(Order order) {
        String orderId = "PO_" + Instant.now().toEpochMilli();
        order.setId(orderId);
        order.setCreatedAt(Instant.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        order.setUpdatedAt(Instant.now());
        return orderRepository.save(order);
    }
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderResponse> getAllOrdersWithDetails() {
        List<Order> orders = getAllOrders();
        return orders.stream().map(this::convertToOrderResponse).collect(Collectors.toList());
    }

    private OrderResponse convertToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomer() != null ? order.getCustomer().getFullName() : null);
        response.setStaffName(order.getStaff() != null ? order.getStaff().getFullName() : null);
        response.setPaymentMethodName(order.getPaymentMethod() != null ? order.getPaymentMethod().getName() : null);
        response.setTransportVendorName(order.getTransportVendor() != null ? order.getTransportVendor().getName() : null);
        response.setVoucherCode(order.getVoucher() != null ? order.getVoucher().getId() : null);
        response.setShippingAddress(order.getShippingAddress());
        response.setStatus(order.getStatus());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        response.setCreatedAt(order.getCreatedAt() != null ? formatter.format(order.getCreatedAt()) : null);
        response.setUpdatedAt(order.getUpdatedAt() != null ? formatter.format(order.getUpdatedAt()) : null);
        
        // Calculate total and discount
        BigDecimal total = calculateOrderTotal(order);
        response.setTotal(total);
        
        if (order.getVoucher() != null) {
            String discountValue = calculateDiscountValue(order.getVoucher(), total);
            response.setDisCountValue(discountValue);
        }
        
        return response;
    }

    private BigDecimal calculateOrderTotal(Order order) {
        return orderDetailRepository.findByOrderId(order.getId()).stream()
                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String calculateDiscountValue(Voucher voucher, BigDecimal total) {
        if (total.compareTo(voucher.getMinOrder()) < 0) {
            return "0";
        }
        
        BigDecimal discountAmount;
        if (voucher.getDiscountType()) {
            // Percentage discount
            discountAmount = total.multiply(BigDecimal.valueOf(voucher.getDiscountValue() / 100));
            if (voucher.getMaxDiscount() != null) {
                discountAmount = discountAmount.min(voucher.getMaxDiscount());
            }
        } else {
            // Fixed amount discount
            discountAmount = BigDecimal.valueOf(voucher.getDiscountValue());
        }
        
        return discountAmount.toString();
    }
}
