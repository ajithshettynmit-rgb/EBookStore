package com.ebookstore.service;

import com.ebookstore.dto.CartItemRequest;
import com.ebookstore.entity.Cart;
import com.ebookstore.entity.CartItem;
import com.ebookstore.entity.Product;
import com.ebookstore.entity.User;
import com.ebookstore.repository.CartItemRepository;
import com.ebookstore.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final UserService userService;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> createCartForUser(userId));
    }

    @Transactional
    private Cart createCartForUser(Long userId) {
        User user = userService.getUserById(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Transactional
    public CartItem addItemToCart(Long userId, CartItemRequest request) {
        Cart cart = getCartByUserId(userId);
        Product product = productService.getProductById(request.getProductId());

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId());

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            return cartItemRepository.save(item);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            return cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public CartItem updateCartItem(Long itemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void removeCartItem(Long itemId) {
        if (!cartItemRepository.existsById(itemId)) {
            throw new RuntimeException("Cart item not found");
        }
        cartItemRepository.deleteById(itemId);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}

// Made with Bob
