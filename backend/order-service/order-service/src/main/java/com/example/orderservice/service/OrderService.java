package com.example.orderservice.service;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public ProductResponse getProduct(Long productId) {
        return productClient.getById(productId);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setCustomerId(orderDetails.getCustomerId());
            existingOrder.setTotalAmount(orderDetails.getTotalAmount());
            existingOrder.setStatus(orderDetails.getStatus());
            existingOrder.setShippingAddress(orderDetails.getShippingAddress());
            return orderRepository.save(existingOrder);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
