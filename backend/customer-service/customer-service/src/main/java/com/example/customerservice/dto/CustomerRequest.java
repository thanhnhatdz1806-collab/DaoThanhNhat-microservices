package com.example.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(max = 200, message = "Tên khách hàng tối đa 200 ký tự")
    String name,

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    String email,

    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Số điện thoại không hợp lệ (9-10 chữ số)")
    String phone,

    String address
) {}
