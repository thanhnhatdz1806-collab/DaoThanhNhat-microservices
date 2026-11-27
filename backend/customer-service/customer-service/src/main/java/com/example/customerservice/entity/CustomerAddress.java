package com.example.customerservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customer_addresses")
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Khách hàng không được để trống")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank(message = "Tên người nhận không được để trống")
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @NotBlank(message = "Số điện thoại nhận hàng không được để trống")
    @Column(nullable = false, length = 20)
    private String phone;

    @NotBlank(message = "Địa chỉ đường phố không được để trống")
    @Column(name = "street_address", nullable = false, length = 500)
    private String streetAddress;

    @Column(length = 100)
    private String city;

    @Column(name = "is_default")
    private Boolean isDefault = false;

    public CustomerAddress() {
    }

    public CustomerAddress(Long id, Customer customer, String recipientName, String phone, String streetAddress, String city, Boolean isDefault) {
        this.id = id;
        this.customer = customer;
        this.recipientName = recipientName;
        this.phone = phone;
        this.streetAddress = streetAddress;
        this.city = city;
        this.isDefault = isDefault != null ? isDefault : false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
