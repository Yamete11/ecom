package durden.company.cart.services;

import durden.company.cart.DTOs.AddToCartEventDTO;
import durden.company.cart.DTOs.CartDTO;
import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;
import durden.company.cart.mappers.CartMapper;
import durden.company.cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
    }

    public CartDTO getOrCreateCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setCreatedAt(LocalDateTime.now());
                    return cartRepository.save(newCart);
                });
        return CartMapper.toDTO(cart);
    }

    public void addToCart(AddToCartEventDTO eventDTO) {
        Cart cart = cartRepository.findByUserId(eventDTO.getUserId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(eventDTO.getUserId());
            newCart.setCreatedAt(LocalDateTime.now());
            return cartRepository.save(newCart);
        });

        cartItemService.addOrUpdateItem(cart, eventDTO.getProductId(), eventDTO.getQuantity());

    }
}
