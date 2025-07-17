package durden.company.cart.mappers;

import durden.company.cart.DTOs.CartItemDTO;
import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;

public class CartItemMapper {
    public static CartItemDTO toDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public static CartItem toEntity(CartItemDTO dto, Cart cart) {
        CartItem item = new CartItem();
        item.setId(dto.getId());
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setCart(cart);
        return item;
    }
}
