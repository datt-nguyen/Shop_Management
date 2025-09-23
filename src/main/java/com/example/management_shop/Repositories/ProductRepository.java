package com.example.management_shop.Repositories;

import com.example.management_shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{
}
