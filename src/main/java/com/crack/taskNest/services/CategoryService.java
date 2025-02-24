package com.crack.taskNest.services;

import com.crack.taskNest.entity.Category;
import com.crack.taskNest.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    // Automatically add default categories when the application starts
    @PostConstruct
    public void addDefaultCategories() {
        if (categoryRepository.count() == 0) { // Only add if empty
            categoryRepository.save(new Category("Work"));
            categoryRepository.save(new Category("Personal"));
            categoryRepository.save(new Category("Shopping"));
        }
    }
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
