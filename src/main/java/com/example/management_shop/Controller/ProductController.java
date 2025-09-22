package com.example.management_shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management_shop.Service.ProductService;
import com.example.management_shop.Entity.Product;

import java.util.List;
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    @PostMapping
    public Product createProduct(@RequestBody Product product) { return productService.saveProduct(product); }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam boolean status) {
        productService.changeStatus(id, status);
        return ResponseEntity.ok("Product status updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }
}

