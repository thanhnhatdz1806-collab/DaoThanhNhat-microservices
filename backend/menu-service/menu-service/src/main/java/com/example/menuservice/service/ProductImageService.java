package com.example.menuservice.service;

import com.example.menuservice.entity.Product;
import com.example.menuservice.entity.ProductImage;
import com.example.menuservice.repository.ProductImageRepository;
import com.example.menuservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    public ProductImageService(ProductImageRepository productImageRepository, ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    public List<ProductImage> getAll() {
        return productImageRepository.findAll();
    }

    public Optional<ProductImage> getById(Long id) {
        return productImageRepository.findById(id);
    }

    public List<ProductImage> getByProductId(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    public ProductImage create(ProductImage image) {
        if (image.getProduct() != null && image.getProduct().getId() != null) {
            Product product = productRepository.findById(image.getProduct().getId()).orElse(null);
            if (product == null) {
                throw new IllegalArgumentException("Sản phẩm với ID " + image.getProduct().getId() + " không tồn tại");
            }
            image.setProduct(product);
        }
        return productImageRepository.save(image);
    }

    public ProductImage update(Long id, ProductImage imageDetails) {
        return productImageRepository.findById(id).map(existingImage -> {
            existingImage.setImageUrl(imageDetails.getImageUrl());
            if (imageDetails.getProduct() != null && imageDetails.getProduct().getId() != null) {
                Product product = productRepository.findById(imageDetails.getProduct().getId()).orElse(null);
                if (product != null) {
                    existingImage.setProduct(product);
                }
            }
            return productImageRepository.save(existingImage);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (productImageRepository.existsById(id)) {
            productImageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
