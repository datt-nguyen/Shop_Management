package com.example.management_shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management_shop.Service.CategoryService;
import com.example.management_shop.Entity.Category;

import java.util.List;
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() { return categoryService.getAllCategories(); }

    @PostMapping
    public Category createCategory(@RequestBody Category category) { return categoryService.saveCategory(category); }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam boolean status) {
        categoryService.changeStatus(id, status);
        return ResponseEntity.ok("Category status updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted");
    }
}

