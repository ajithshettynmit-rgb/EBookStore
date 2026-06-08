package com.ebookstore.controller;

import com.ebookstore.dto.OrderRequest;
import com.ebookstore.entity.Order;
import com.ebookstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(@RequestParam Long userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestParam Long userId,
            @Valid @RequestBody OrderRequest request) {
        Order order = orderService.createOrder(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long id,
            @RequestParam Long userId) {
        Order order = orderService.cancelOrder(id, userId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/reorder")
    public ResponseEntity<Order> reorder(
            @PathVariable Long id,
            @RequestParam Long userId) {
        Order order = orderService.reorder(id, userId);
        return ResponseEntity.ok(order);
    }
}

// Made with Bob
