package com.example.management_shop.Controller;

import com.example.management_shop.Entity.Category;
import com.example.management_shop.Entity.Users;
import com.example.management_shop.Repositories.CategoryRepository;
import com.example.management_shop.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // Get all users (ADMIN only per SecurityConfig)
    @GetMapping("/users")
    public List<Users> getAllUsers() { return userRepository.findAll(); }

    // Create user (ADMIN)
    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Delete user by id (ADMIN)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) return ResponseEntity.notFound().build();
        userRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    // List categories (MANAGER, ADMIN) — note: SecurityConfig restricts /api/admin/** to ADMIN,
    // if you need MANAGER also, adjust SecurityConfig or add method-level @PreAuthorize.
    @GetMapping("/categories")
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }
}

