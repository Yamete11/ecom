package durden.company.cart.services;

import durden.company.cart.DTOs.*;
import durden.company.cart.entities.Cart;
import durden.company.cart.entities.CartItem;
import durden.company.cart.kafka.CartKafkaProducer;
import durden.company.cart.mappers.CartMapper;
import durden.company.cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
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

    public void checkoutCart(CartCheckoutRequestDTO request) {
        Long userId = request.getUserId();
        Long paymentMethodId = request.getPaymentMethodId();

        List<CartItem> cartItems = cartItemService.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cart is empty for user ID: " + userId);
        }

        List<Long> productIds = cartItems.stream()
                .map(CartItem::getProductId)
                .toList();

        ProductDTO[] productDTOs = restTemplate.postForObject(
                productServiceUrl + "/batch",
                productIds,
                ProductDTO[].class
        );

        if (productDTOs == null || productDTOs.length == 0) {
            throw new IllegalStateException("No product information found for cart items");
        }

        Map<Long, Integer> productIdToQuantity = cartItems.stream()
                .collect(Collectors.toMap(CartItem::getProductId, CartItem::getQuantity));

        BigDecimal totalPrice = Arrays.stream(productDTOs)
                .map(p -> {
                    BigDecimal price = p.getPrice();
                    int quantity = productIdToQuantity.getOrDefault(p.getId(), 0);
                    return price != null ? price.multiply(BigDecimal.valueOf(quantity)) : BigDecimal.ZERO;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<CartItemDTO> itemDTOs = Arrays.stream(productDTOs)
                .map(p -> new CartItemDTO(
                        p.getId(),
                        productIdToQuantity.getOrDefault(p.getId(), 0),
                        p.getPrice(),
                        p.getCategory()))
                .toList();

        CartCheckoutEventDTO eventDTO = new CartCheckoutEventDTO();
        eventDTO.setUserId(userId);
        eventDTO.setPaymentMethodId(paymentMethodId);
        eventDTO.setTotalPrice(totalPrice);
        eventDTO.setItems(itemDTOs);

        cartKafkaProducer.sendCartCheckoutEvent(eventDTO);
    }



}
