package com.example.orderservice.service;

import com.example.orderservice.entity.Cart;
import com.example.orderservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getById(Long id) {
        return cartRepository.findById(id);
    }

    public List<Cart> getByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart update(Long id, Cart cartDetails) {
        return cartRepository.findById(id).map(existingCart -> {
            existingCart.setCustomerId(cartDetails.getCustomerId());
            return cartRepository.save(existingCart);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
