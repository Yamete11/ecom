package com.example.ecom.DTOs;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private Float price;
    private String category;
}
