package com.example.ecom.services;

import com.example.ecom.DTOs.AddToCartRequestDTO;
import com.example.ecom.DTOs.ProductDTO;
import com.example.ecom.entities.Category;
import com.example.ecom.entities.Product;
import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.kafka.CartKafkaProducer;
import com.example.ecom.mappers.ProductMapper;
import com.example.ecom.repositories.ProductRepository;
import com.example.ecom.security.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartKafkaProducer cartKafkaProducer;
    private final JwtCore jwtCore;


    @Autowired
    public ProductService(ProductRepository productRepository, CartKafkaProducer cartKafkaProducer, JwtCore jwtCore) {
        this.productRepository = productRepository;
        this.cartKafkaProducer = cartKafkaProducer;
        this.jwtCore = jwtCore;
    }

    public ProductDTO getProductById(long id) {
        return ProductMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id)));
    }

    public List<ProductDTO> findAll() {
        return ProductMapper.toDTOList(productRepository.findAll());
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public void deleteProductsByCategoryId(long id){
        productRepository.deleteByCategoryId(id);
    }

    public void addProductToCart(Long productId, String token) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        Long userId = jwtCore.getUserId(token);

        AddToCartRequestDTO event = new AddToCartRequestDTO(
                userId,
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory().getTitle(),
                UUID.randomUUID().toString()
        );

        cartKafkaProducer.sendAddToCartEvent(event);
    }
}
