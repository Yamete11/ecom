package durden.company.cart.controllers;

import durden.company.cart.DTOs.CartCheckoutEventDTO;
import durden.company.cart.DTOs.CartDTO;
import durden.company.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    public final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.getOrCreateCartByUserId(userId),  HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkoutCart(@RequestBody CartCheckoutEventDTO request) {
        cartService.checkoutCart(request);
        return ResponseEntity.ok("Checkout event sent!");
    }

}
