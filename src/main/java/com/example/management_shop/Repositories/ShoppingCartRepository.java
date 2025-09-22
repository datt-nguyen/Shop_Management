package com.example.management_shop.Repositories;

import com.example.management_shop.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
    List<ShoppingCart> findByUserId(Long userId);
}
