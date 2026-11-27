package com.example.orderservice.service;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.OrderItemRequest;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productClient = productClient;
    }

    public ProductResponse getProduct(Long productId) {
        return productClient.getById(productId);
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        // 1. Khởi tạo đơn hàng
        Order order = new Order();
        order.setCustomerId(request.customerId() != null ? request.customerId() : 1L);
        order.setStatus("PENDING");
        order.setShippingAddress(request.shippingAddress() != null ? request.shippingAddress() : "Tại quán");
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        // 2. Duyệt qua từng sản phẩm trong yêu cầu đặt hàng
        for (OrderItemRequest item : request.items()) {
            // Gọi Product Service (Menu Service) để lấy thông tin và đơn giá
            ProductResponse product = productClient.getById(item.productId());

            // Tính thành tiền: đơn giá x số lượng
            BigDecimal amount = product.price().multiply(BigDecimal.valueOf(item.quantity()));

            // Tính tổng tiền đơn hàng
            totalAmount = totalAmount.add(amount);

            // Tạo OrderItem tương ứng
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProductId(item.productId());
            orderItem.setQuantity(item.quantity());
            orderItem.setUnitPrice(product.price());

            orderItems.add(orderItem);
        }

        // 3. Cập nhật tổng tiền đơn hàng
        savedOrder.setTotalAmount(totalAmount);
        orderRepository.save(savedOrder);

        // 4. Lưu danh sách chi tiết đơn hàng (order_items)
        orderItemRepository.saveAll(orderItems);

        // 5. Trả về OrderResponse
        return new OrderResponse(savedOrder.getId(), savedOrder.getTotalAmount());
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
