package com.example.ecom.services;

import com.example.ecom.DTOs.ProductDTO;
import com.example.ecom.entities.Category;
import com.example.ecom.entities.Product;
import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.mappers.ProductMapper;
import com.example.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO getProductById(long id) {
        return ProductMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id)));
    }

    public List<ProductDTO> getProductsByIds(List<Long> ids) {
        return ProductMapper.toDTOList(productRepository.findAllById(ids));
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
}
