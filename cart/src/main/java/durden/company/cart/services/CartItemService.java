package durden.company.cart.services;

import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;
import durden.company.cart.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addOrUpdateItem(Cart cart, Long productId, int quantity) {
        CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .map(existingItem -> {
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                    return existingItem;
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProductId(productId);
                    newItem.setQuantity(quantity);
                    return newItem;
                });

        cartItemRepository.save(item);
    }
}
