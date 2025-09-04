package com.example.ecom.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequestDTO {
    private Long userId;
    private Long id;
    private String title;
    private BigDecimal price;
    private String category;
    private String eventId;
}
