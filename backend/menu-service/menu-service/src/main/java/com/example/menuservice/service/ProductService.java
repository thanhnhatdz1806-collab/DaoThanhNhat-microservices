package com.example.menuservice.service;

import com.example.menuservice.dto.ProductRequest;
import com.example.menuservice.dto.ProductResponse;
import com.example.menuservice.entity.Category;
import com.example.menuservice.entity.Product;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.repository.CategoryRepository;
import com.example.menuservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<ProductResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy sản phẩm: " + id
                        )
                );
        return toResponse(product);
    }

    public List<ProductResponse> getByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity() != null ? request.quantity() : 0);

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId()).orElse(null);
            product.setCategory(category);
        }

        Product saved = repository.save(product);
        return toResponse(saved);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm: " + id));

        existingProduct.setName(request.name());
        existingProduct.setPrice(request.price());
        if (request.quantity() != null) {
            existingProduct.setQuantity(request.quantity());
        }
        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId()).orElse(null);
            existingProduct.setCategory(category);
        }

        Product saved = repository.save(existingProduct);
        return toResponse(saved);
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm: " + id);
        }
        repository.deleteById(id);
        return true;
    }
}
