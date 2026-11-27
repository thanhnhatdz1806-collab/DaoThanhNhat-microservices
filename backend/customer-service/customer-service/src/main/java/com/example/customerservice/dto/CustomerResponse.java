package com.example.customerservice.dto;

public record CustomerResponse(
    Long id,
    String name,
    String email,
    String phone,
    String address
) {}
