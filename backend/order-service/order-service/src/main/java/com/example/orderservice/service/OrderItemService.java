package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getById(Long id) {
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> getByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem create(OrderItem item) {
        if (item.getOrder() != null && item.getOrder().getId() != null) {
            Order order = orderRepository.findById(item.getOrder().getId()).orElse(null);
            if (order == null) {
                throw new IllegalArgumentException("Đơn hàng không tồn tại");
            }
            item.setOrder(order);
        }
        return orderItemRepository.save(item);
    }

    public OrderItem update(Long id, OrderItem details) {
        return orderItemRepository.findById(id).map(existingItem -> {
            existingItem.setProductId(details.getProductId());
            existingItem.setQuantity(details.getQuantity());
            existingItem.setUnitPrice(details.getUnitPrice());
            return orderItemRepository.save(existingItem);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
