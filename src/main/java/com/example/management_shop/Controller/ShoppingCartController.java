package com.example.management_shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management_shop.Service.ShoppingCartService;
import com.example.management_shop.Entity.ShoppingCart;

import java.util.List;
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService cartService;

    @GetMapping("/{userId}")
    public List<ShoppingCart> getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping
    public ShoppingCart addToCart(@RequestBody ShoppingCart cart) {
        return cartService.addToCart(cart);
    }

    @PutMapping("/{id}")
    public ShoppingCart updateQuantity(@PathVariable Long id, @RequestParam int quantity) {
        return cartService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItem(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared");
    }
}

