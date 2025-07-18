package durden.company.cart.mappers;

import durden.company.cart.DTOs.CartDTO;
import durden.company.cart.DTOs.CartItemDTO;
import durden.company.cart.DTOs.ProductDTO;
import durden.company.cart.entities.Cart;

import java.util.List;

// CartMapper.java
public class CartMapper {
    public static CartDTO toDTO(Cart cart, List<ProductDTO> products) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setCartItems(products);
        return dto;
    }
}


