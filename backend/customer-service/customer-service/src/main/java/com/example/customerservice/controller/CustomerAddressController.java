package com.example.customerservice.controller;

import com.example.customerservice.entity.CustomerAddress;
import com.example.customerservice.service.CustomerAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-addresses")
public class CustomerAddressController {

    private final CustomerAddressService service;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CustomerAddress>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerAddress>> getByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<CustomerAddress> create(@Valid @RequestBody CustomerAddress address) {
        try {
            CustomerAddress created = service.create(address);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> update(@PathVariable Long id, @Valid @RequestBody CustomerAddress address) {
        CustomerAddress updated = service.update(id, address);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
