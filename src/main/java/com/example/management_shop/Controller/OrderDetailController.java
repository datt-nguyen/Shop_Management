package com.example.management_shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.management_shop.Service.OrderDetailService;
import com.example.management_shop.Entity.OrderDetail;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping("/{orderId}")
    public List<OrderDetail> getOrderDetails(@PathVariable Long orderId) {
        return orderDetailService.getOrderDetails(orderId);
    }
}

