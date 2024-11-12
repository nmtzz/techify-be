package app.techify.service;

import app.techify.entity.Order;
import app.techify.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

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
}
