package com.ebookstore.controller;

import com.ebookstore.dto.CartItemRequest;
import com.ebookstore.entity.Cart;
import com.ebookstore.entity.CartItem;
import com.ebookstore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/items")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestParam Long userId,
            @Valid @RequestBody CartItemRequest request) {
        CartItem cartItem = cartService.addItemToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body) {
        Integer quantity = body.get("quantity");
        CartItem cartItem = cartService.updateCartItem(id, quantity);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }
}

// Made with Bob
