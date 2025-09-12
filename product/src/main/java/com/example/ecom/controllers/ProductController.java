package com.example.ecom.controllers;

import com.example.ecom.DTOs.AddProductToCartDTO;
import com.example.ecom.DTOs.ProductDTO;
import com.example.ecom.entities.Product;
import com.example.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        return new ResponseEntity<>(productService.findAll(),  HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable long id) {
        return new ResponseEntity<>(productService.getProductById(id),  HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        Product updatedProduct = productService.update(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<Map<String, String>> addToCart(
            @RequestBody Long productId,
            @CookieValue("accessToken") String token) {

        productService.addProductToCart(productId, token);
        return ResponseEntity.ok(Map.of("message", "Product sent to cart"));
    }

}