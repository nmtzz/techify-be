package app.techify.controller;

import app.techify.entity.OrderDetail;
import app.techify.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/order_detail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    @PostMapping("")
    public ResponseEntity<Void> createOrderDetail(@RequestBody List<OrderDetail> orderDetails) {
        orderDetailService.createOrderDetail(orderDetails);
        return ResponseEntity.ok().build();
    }
}
