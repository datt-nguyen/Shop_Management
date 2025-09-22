package com.example.management_shop.Service;

import com.example.management_shop.Repositories.CategoryRepository;
import com.example.management_shop.Entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class    CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void changeStatus(Long id, boolean status) {
        categoryRepository.findById(id).ifPresent(c -> {
            c.setStatus(status);
            categoryRepository.save(c);
        });
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

