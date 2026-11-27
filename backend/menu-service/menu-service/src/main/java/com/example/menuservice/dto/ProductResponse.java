package com.example.menuservice.dto;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    BigDecimal price
) {}
