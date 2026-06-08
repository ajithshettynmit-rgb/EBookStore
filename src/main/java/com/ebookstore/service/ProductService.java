package com.ebookstore.service;

import com.ebookstore.dto.ProductRequest;
import com.ebookstore.entity.Product;
import com.ebookstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(String category, String brand, String search) {
        if (search != null && !search.isEmpty()) {
            return productRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search);
        } else if (category != null && brand != null) {
            return productRepository.findByCategoryAndBrand(category, brand);
        } else if (category != null) {
            return productRepository.findByCategory(category);
        } else if (brand != null) {
            return productRepository.findByBrand(brand);
        } else {
            return productRepository.findAll();
        }
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setAuthor(request.getAuthor());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {
        Product product = getProductById(id);
        
        product.setTitle(request.getTitle());
        product.setAuthor(request.getAuthor());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public List<Product> getRelatedProducts(Long productId) {
        Product product = getProductById(productId);
        return productRepository.findByCategory(product.getCategory())
                .stream()
                .filter(p -> !p.getId().equals(productId))
                .limit(5)
                .toList();
    }
}

// Made with Bob
