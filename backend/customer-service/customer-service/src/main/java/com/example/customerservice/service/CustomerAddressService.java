package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.CustomerAddress;
import com.example.customerservice.repository.CustomerAddressRepository;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public CustomerAddressService(CustomerAddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public List<CustomerAddress> getAll() {
        return addressRepository.findAll();
    }

    public Optional<CustomerAddress> getById(Long id) {
        return addressRepository.findById(id);
    }

    public List<CustomerAddress> getByCustomerId(Long customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    public CustomerAddress create(CustomerAddress address) {
        if (address.getCustomer() != null && address.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(address.getCustomer().getId()).orElse(null);
            if (customer == null) {
                throw new IllegalArgumentException("Khách hàng không tồn tại");
            }
            address.setCustomer(customer);
        }
        return addressRepository.save(address);
    }

    public CustomerAddress update(Long id, CustomerAddress details) {
        return addressRepository.findById(id).map(existing -> {
            existing.setRecipientName(details.getRecipientName());
            existing.setPhone(details.getPhone());
            existing.setStreetAddress(details.getStreetAddress());
            existing.setCity(details.getCity());
            existing.setIsDefault(details.getIsDefault());
            return addressRepository.save(existing);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
