package com.example.menuservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 200, message = "Tên sản phẩm tối đa 200 ký tự")
    String name,

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    BigDecimal price,

    @PositiveOrZero(message = "Số lượng phải >= 0")
    Integer quantity,

    Long categoryId
) {
    public ProductRequest(String name, BigDecimal price) {
        this(name, price, 0, null);
    }
}
