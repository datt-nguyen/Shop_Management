package com.example.management_shop.Service;

import com.example.management_shop.Repositories.ProductRepository;
import com.example.management_shop.Entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void changeStatus(Long id, boolean status) {
        productRepository.findById(id).ifPresent(p -> {
            p.setStatus(status);
            productRepository.save(p);
        });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
