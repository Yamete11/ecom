package durden.company.cart.services;

import durden.company.cart.DTOs.*;
import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;
import durden.company.cart.kafka.CartKafkaProducer;
import durden.company.cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final RestTemplate restTemplate;
    private final CartKafkaProducer cartKafkaProducer;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService, RestTemplate  restTemplate, CartKafkaProducer cartKafkaProducer) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.restTemplate = restTemplate;
        this.cartKafkaProducer = cartKafkaProducer;
    }

    public List<CartItemDTO> getOrCreateCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setCreatedAt(LocalDateTime.now());
                    return cartRepository.save(newCart);
                });

        List<CartItem> cartItems = cartItemService.findCartItemsByCartId(cart.getId());

        return cartItems.stream()
                .map(item -> new CartItemDTO(
                        item.getProductId(),
                        item.getTitle(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getCategory()
                )).toList();
    }

    public void addToCart(AddToCartEventDTO addToCartEventDTO) {
        Cart cart = cartRepository.findByUserId(addToCartEventDTO.getUserId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(addToCartEventDTO.getUserId());
            newCart.setCreatedAt(LocalDateTime.now());
            return cartRepository.save(newCart);
        });

        cartItemService.addOrUpdateItem(cart, addToCartEventDTO);
    }

    public void checkoutCart(CartCheckoutRequestDTO request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
