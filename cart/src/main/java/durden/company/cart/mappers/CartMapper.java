package durden.company.cart.mappers;

import durden.company.cart.DTOs.CartDTO;
import durden.company.cart.DTOs.CartItemDTO;
import durden.company.cart.DTOs.ProductDTO;
import durden.company.cart.entities.Cart;

import java.util.List;

public class CartMapper {
    public static CartDTO toDTO(Cart cart, List<CartItemDTO> products) {
        CartDTO dto = new CartDTO();
        dto.setUserId(cart.getUserId());
        dto.setCartItems(products);
        return dto;
    }
}


