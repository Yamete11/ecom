package com.example.ecom.repositories;

import com.example.ecom.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    @Query("SELECT p.category FROM Product p WHERE p.id = :productId")
    Optional<Category> findCategoryByProductId(@Param("productId") long id);
}
