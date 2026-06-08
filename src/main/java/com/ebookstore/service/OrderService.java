package com.ebookstore.service;

import com.ebookstore.dto.OrderRequest;
import com.ebookstore.entity.*;
import com.ebookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;

    @Transactional
    public Order createOrder(Long userId, OrderRequest request) {
        User user = userService.getUserById(userId);
        Cart cart = cartService.getCartByUserId(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Calculate total
        double total = cart.getTotal();

        // Apply gift points discount
        int giftPointsToUse = Math.min(request.getUseGiftPoints(), user.getGiftPoints());
        double discount = giftPointsToUse * 1.0; // 1 point = 1 currency unit
        total = Math.max(0, total - discount);

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(total);
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setStatus(Order.OrderStatus.CONFIRMED);
        order.setGiftPointsUsed(giftPointsToUse);

        // Create order items from cart items
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            order.getItems().add(orderItem);
        }

        // Update user gift points
        user.setGiftPoints(user.getGiftPoints() - giftPointsToUse);
        // Award new gift points (10% of order value)
        user.setGiftPoints(user.getGiftPoints() + (int) (total * 0.1));

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartService.clearCart(userId);

        return savedOrder;
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public Order cancelOrder(Long orderId, Long userId) {
        Order order = getOrderById(orderId);

        if (!order.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to cancel this order");
        }

        if (order.getStatus() == Order.OrderStatus.CANCELLED) {
            throw new RuntimeException("Order is already cancelled");
        }

        // Check if order is within 48 hours
        Duration duration = Duration.between(order.getOrderDate(), LocalDateTime.now());
        if (duration.toHours() > 48) {
            throw new RuntimeException("Cannot cancel order after 48 hours");
        }

        order.setStatus(Order.OrderStatus.CANCELLED);

        // Refund gift points
        User user = order.getUser();
        user.setGiftPoints(user.getGiftPoints() + order.getGiftPointsUsed());

        return orderRepository.save(order);
    }

    @Transactional
    public Order reorder(Long orderId, Long userId) {
        Order originalOrder = getOrderById(orderId);

        if (!originalOrder.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to reorder this order");
        }

        // Add items to cart
        Cart cart = cartService.getCartByUserId(userId);
        
        for (OrderItem orderItem : originalOrder.getItems()) {
            boolean itemExists = false;
            for (CartItem cartItem : cart.getItems()) {
                if (cartItem.getProduct().getId().equals(orderItem.getProduct().getId())) {
                    cartItem.setQuantity(cartItem.getQuantity() + orderItem.getQuantity());
                    itemExists = true;
                    break;
                }
            }
            
            if (!itemExists) {
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(cart);
                newCartItem.setProduct(orderItem.getProduct());
                newCartItem.setQuantity(orderItem.getQuantity());
                cart.getItems().add(newCartItem);
            }
        }

        return originalOrder;
    }
}

// Made with Bob
