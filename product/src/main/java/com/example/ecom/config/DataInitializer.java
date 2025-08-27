package com.example.ecom.config;

import com.example.ecom.entities.Category;
import com.example.ecom.entities.Product;
import com.example.ecom.repositories.CategoryRepository;
import com.example.ecom.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

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

        productRepository.save(createProduct("Smartphone", BigDecimal.valueOf(120.00), electronics));
        productRepository.save(createProduct("Laptop", BigDecimal.valueOf(175.00), electronics));
        productRepository.save(createProduct("Headphones", BigDecimal.valueOf(20.00), electronics));

        productRepository.save(createProduct("Novel", BigDecimal.valueOf(45.00), books));
        productRepository.save(createProduct("Cookbook", BigDecimal.valueOf(80.00), books));
        productRepository.save(createProduct("Science Fiction", BigDecimal.valueOf(101.00), books));

        productRepository.save(createProduct("T-shirt", BigDecimal.valueOf(95.00), clothing));
        productRepository.save(createProduct("Jeans", BigDecimal.valueOf(37.00), clothing));
        productRepository.save(createProduct("Jacket", BigDecimal.valueOf(25.00), clothing));

        productRepository.save(createProduct("Coffee Maker", BigDecimal.valueOf(77.00), home));
        productRepository.save(createProduct("Blender", BigDecimal.valueOf(23.00), home));
        productRepository.save(createProduct("Vacuum Cleaner", BigDecimal.valueOf(44.00), home));

    }

    private Product createProduct(String title, BigDecimal price, Category category) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
}
