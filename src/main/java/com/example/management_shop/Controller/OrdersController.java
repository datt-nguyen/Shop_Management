package com.example.management_shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management_shop.Service.OrdersService;
import com.example.management_shop.Entity.Orders;

import java.util.List;
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/{userId}")
    public List<Orders> getOrders(@PathVariable Long userId) {
        return ordersService.getOrdersByUser(userId);
    }

    @PostMapping("/{userId}")
    public Orders createOrder(@PathVariable Long userId, @RequestBody Orders order) {
        return ordersService.createOrder(userId, order);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        ordersService.changeStatus(id, status);
        return ResponseEntity.ok("Order status updated");
    }

    @PostMapping("/checkout/{userId}")
    public Orders checkout(@PathVariable Long userId) {
        return ordersService.checkout(userId);
    }
}
