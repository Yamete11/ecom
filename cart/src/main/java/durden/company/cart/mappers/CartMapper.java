package durden.company.cart.mappers;

import durden.company.cart.DTOs.CartDTO;
import durden.company.cart.DTOs.CartItemDTO;
import durden.company.cart.entities.Cart;

import java.util.List;

public class CartMapper {
    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setCreatedAt(cart.getCreatedAt());

        List<CartItemDTO> itemDTOs = cart.getCartItems().stream()
                .map(CartItemMapper::toDTO)
                .toList();
        dto.setCartItems(itemDTOs);
        return dto;
    }
}

