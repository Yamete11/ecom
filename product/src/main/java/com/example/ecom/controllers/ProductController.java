package com.example.ecom.controllers;

import com.example.ecom.DTOs.AddToCartEventDTO;
import com.example.ecom.DTOs.AddToCartRequestDTO;
import com.example.ecom.DTOs.ProductDTO;
import com.example.ecom.entities.Product;
import com.example.ecom.kafka.CartKafkaProducer;
import com.example.ecom.services.CartIntegrationService;
import com.example.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CartIntegrationService cartIntegrationService;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService, CartIntegrationService cartIntegrationService) {
        this.productService = productService;
        this.cartIntegrationService = cartIntegrationService;
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
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequestDTO request) {
        cartIntegrationService.addProductToCart(request.getUserId(), request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Product sent to cart");
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ProductDTO>> getProductsByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(productService.getProductsByIds(ids));
    }

}
