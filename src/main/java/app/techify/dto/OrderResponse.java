package app.techify.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderResponse {
    private String id;
    private String customerName;
    private String staffName;
    private String paymentMethodName;
    private String transportVendorName;
    private String voucherCode;
    private String shippingAddress;
    private Short status;
    private String createdAt;
    private String updatedAt;
    private String disCountValue;
    private BigDecimal total;
}
