package durden.company.cart.services;

import durden.company.cart.DTOs.AddToCartEventDTO;
import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;
import durden.company.cart.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addOrUpdateItem(Cart cart, AddToCartEventDTO addToCartEventDTO) {
        CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), addToCartEventDTO.getId())
                .map(existingItem -> {
                    existingItem.setQuantity(existingItem.getQuantity() + 1);
                    return existingItem;
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setProductId(addToCartEventDTO.getId());
                    newItem.setTitle(addToCartEventDTO.getTitle());
                    newItem.setPrice(addToCartEventDTO.getPrice());
                    newItem.setCategory(addToCartEventDTO.getCategory());
                    newItem.setQuantity(1);
                    newItem.setCart(cart);
                    return newItem;
                });

        cartItemRepository.save(item);
    }

    public List<CartItem> findCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public boolean deleteCartItem(Long productId) {
        int deletedCount = cartItemRepository.deleteByProductId(productId);
        return deletedCount > 0;
    }

}
