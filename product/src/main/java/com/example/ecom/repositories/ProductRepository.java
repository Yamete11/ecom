package com.example.ecom.repositories;

import com.example.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    void deleteByCategoryId(Long categoryId);
}
