package com.example.management_shop.Service;

import com.example.management_shop.Repositories.ShoppingCartRepository;
import com.example.management_shop.Entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getCartByUser(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    public ShoppingCart addToCart(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart updateQuantity(Long id, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findById(id).orElseThrow();
        cart.setQuantity(quantity);
        return shoppingCartRepository.save(cart);
    }

    public void removeFromCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    public void clearCart(Long userId) {
        List<ShoppingCart> carts = shoppingCartRepository.findByUserId(userId);
        shoppingCartRepository.deleteAll(carts);
    }
}

