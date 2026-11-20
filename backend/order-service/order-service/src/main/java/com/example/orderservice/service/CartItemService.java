package com.example.orderservice.service;

import com.example.orderservice.entity.Cart;
import com.example.orderservice.entity.CartItem;
import com.example.orderservice.repository.CartItemRepository;
import com.example.orderservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getById(Long id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> getByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem create(CartItem cartItem) {
        if (cartItem.getCart() != null && cartItem.getCart().getId() != null) {
            Cart cart = cartRepository.findById(cartItem.getCart().getId()).orElse(null);
            if (cart == null) {
                throw new IllegalArgumentException("Giỏ hàng không tồn tại");
            }
            cartItem.setCart(cart);
        }
        return cartItemRepository.save(cartItem);
    }

    public CartItem update(Long id, CartItem details) {
        return cartItemRepository.findById(id).map(existingItem -> {
            existingItem.setProductId(details.getProductId());
            existingItem.setQuantity(details.getQuantity());
            existingItem.setPrice(details.getPrice());
            return cartItemRepository.save(existingItem);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
