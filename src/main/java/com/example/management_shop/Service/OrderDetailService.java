package com.example.management_shop.Service;

import com.example.management_shop.Repositories.OrderDetailRepository;
import com.example.management_shop.Entity.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getOrderDetails(Long orderId) {
        return orderDetailRepository.findAll()
                .stream()
                .filter(d -> d.getOrders().getId().equals(orderId))
                .toList();
    }
}

