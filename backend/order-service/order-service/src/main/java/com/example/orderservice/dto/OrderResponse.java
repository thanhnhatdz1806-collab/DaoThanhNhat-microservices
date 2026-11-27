package com.example.orderservice.dto;

import java.math.BigDecimal;

public record OrderResponse(
    Long id,
    BigDecimal totalAmount
) {}
