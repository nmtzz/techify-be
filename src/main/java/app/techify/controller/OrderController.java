package app.techify.controller;

import app.techify.entity.Order;
import app.techify.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PutMapping("")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

}
