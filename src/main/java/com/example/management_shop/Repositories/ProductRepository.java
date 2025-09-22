package com.example.management_shop.Repositories;

import com.example.management_shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long>{
}
