package com.example.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderRequest(
    Long customerId,
    String shippingAddress,
    @NotEmpty(message = "Đơn hàng phải có sản phẩm")
    List<@NotNull @Valid OrderItemRequest> items
) {
    public OrderRequest(List<@NotNull @Valid OrderItemRequest> items) {
        this(1L, "Tại quán", items);
    }
}
