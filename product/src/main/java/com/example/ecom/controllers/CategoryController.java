package com.example.ecom.controllers;

import com.example.ecom.DTOs.CategoryDTO;
import com.example.ecom.entities.Category;
import com.example.ecom.entities.Product;
import com.example.ecom.services.CategoryService;
import com.example.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(),  HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id),  HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByProductId(@PathVariable long id) {
        return new ResponseEntity<>(categoryService.getCategoryByProductId(id),  HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category) {
        category.setId(id);
        Category updatedCategory = categoryService.update(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
