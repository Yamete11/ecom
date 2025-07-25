package com.example.ecom.services;

import com.example.ecom.DTOs.CategoryDTO;
import com.example.ecom.entities.Category;
import com.example.ecom.repositories.CategoryRepository;
import com.example.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }


    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
    }

    public CategoryDTO getCategoryByProductId(long id) {
        Category category =  categoryRepository.findCategoryByProductId(id).orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
        return new CategoryDTO(category.getId(), category.getTitle());
    }

    public List<CategoryDTO> findAll() {
        List<Category> category = categoryRepository.findAll();

        return category.stream()
                .map(c -> new CategoryDTO(c.getId(), c.getTitle()))
                .toList();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(long id) {
        productService.deleteProductsByCategoryId(id);
        categoryRepository.deleteById(id);
    }
}
