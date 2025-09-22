package com.example.management_shop.Service;

import com.example.management_shop.Entity.OrderDetail;
import com.example.management_shop.Entity.ShoppingCart;
import com.example.management_shop.Entity.Users;
import com.example.management_shop.Repositories.OrderDetailRepository;
import com.example.management_shop.Repositories.OrdersRepository;
import com.example.management_shop.Entity.Orders;
import com.example.management_shop.Repositories.ShoppingCartRepository;
import com.example.management_shop.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;

    public List<Orders> getOrdersByUser(Long userId) {
        return ordersRepository.findByUser_Id(userId);
    }

    public Orders   createOrder(Long userId, Orders order) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);
        return ordersRepository.save(order);
    }

    public void changeStatus(Long id, String status) {
        ordersRepository.findById(id).ifPresent(o -> {
            o.setStatus(status);
            ordersRepository.save(o);
        });
    }


    public Orders checkout(Long userId) {
        List<ShoppingCart> carts = shoppingCartRepository.findByUserId(userId);
        if (carts.isEmpty()) throw new RuntimeException("Cart is empty!");

        Orders order = new Orders();
        order.setUser(carts.get(0).getUser());
        order.setOrderDate(java.time.LocalDateTime.now());
        order.setStatus("PENDING");
        order = ordersRepository.save(order);

        for (ShoppingCart cart : carts) {
            OrderDetail detail = new OrderDetail();
            detail.setOrders(order);
            detail.setProduct(cart.getProduct());
            detail.setQuantity(cart.getQuantity());
            detail.setUnitPrice(cart.getProduct().getPrice());
            orderDetailRepository.save(detail);
        }

        shoppingCartRepository.deleteAll(carts);
        return order;
    }
}

