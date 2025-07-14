package com.example.ecom.config;

import com.example.ecom.entities.Category;
import com.example.ecom.entities.Product;
import com.example.ecom.repositories.CategoryRepository;
import com.example.ecom.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Category electronics = new Category();
        electronics.setTitle("Electronics");
        categoryRepository.save(electronics);

        Category books = new Category();
        books.setTitle("Books");
        categoryRepository.save(books);

        Category clothing = new Category();
        clothing.setTitle("Clothing");
        categoryRepository.save(clothing);

        Category home = new Category();
        home.setTitle("Home & Kitchen");
        categoryRepository.save(home);

        productRepository.save(createProduct("Smartphone", 120, electronics));
        productRepository.save(createProduct("Laptop", 175, electronics));
        productRepository.save(createProduct("Headphones", 20, electronics));

        productRepository.save(createProduct("Novel", 45, books));
        productRepository.save(createProduct("Cookbook", 80, books));
        productRepository.save(createProduct("Science Fiction", 101, books));

        productRepository.save(createProduct("T-shirt", 95, clothing));
        productRepository.save(createProduct("Jeans", 37, clothing));
        productRepository.save(createProduct("Jacket", 25, clothing));

        productRepository.save(createProduct("Coffee Maker", 77, home));
        productRepository.save(createProduct("Blender", 23, home));
        productRepository.save(createProduct("Vacuum Cleaner", 44, home));
    }

    private Product createProduct(String title, float price, Category category) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
}
