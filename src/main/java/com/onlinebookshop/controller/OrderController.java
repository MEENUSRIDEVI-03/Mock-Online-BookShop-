package com.onlinebookshop.controller;

import com.onlinebookshop.model.*;
import com.onlinebookshop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }

    @PostMapping
    public Order placeOrder(@RequestParam Long userId, @RequestBody List<OrderItem> items) {
        return service.placeOrder(userId, items);
    }

    @GetMapping("/user/{id}")
    public List<Order> getUserOrders(@PathVariable Long id) {
        return service.getOrdersByUser(id);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }
}
