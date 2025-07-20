package durden.company.cart.services;

import durden.company.cart.DTOs.AddToCartEventDTO;
import durden.company.cart.DTOs.CartCheckoutEventDTO;
import durden.company.cart.DTOs.CartDTO;
import durden.company.cart.DTOs.ProductDTO;
import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;
import durden.company.cart.kafka.CartKafkaProducer;
import durden.company.cart.mappers.CartMapper;
import durden.company.cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public CartDTO getOrCreateCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setCreatedAt(LocalDateTime.now());
                    return cartRepository.save(newCart);
                });


        List<CartItem> cartItems = cartItemService.findCartItemsByCartId(cart.getId());

        List<Long> productIds = cartItems.stream()
                .map(CartItem::getProductId)
                .distinct()
                .toList();

        ProductDTO[] productDTOs = restTemplate.postForObject(
                productServiceUrl + "/batch",
                productIds,
                ProductDTO[].class
        );


        List<ProductDTO> fullProducts = Arrays.stream(productDTOs)
                .map(p -> new ProductDTO(
                        p.getId(),
                        p.getTitle(),
                        p.getPrice(),
                        p.getCategory()
                ))
                .toList();

        return CartMapper.toDTO(cart, fullProducts);
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

    public void checkoutCart(CartCheckoutEventDTO request) {
        cartKafkaProducer.sendCartCheckoutEvent(request);
    }
}
