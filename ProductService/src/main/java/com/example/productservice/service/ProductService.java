package com.example.productservice.service;

import com.example.productservice.client.ReviewClient;
import com.example.productservice.dto.ProductWithReviews;
import com.example.productservice.dto.ReviewDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewClient reviewClient;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductWithReviews getProductWithReviews(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with id " + productId + " not found"));

        List<ReviewDTO> reviews = reviewClient.getReviewsByProductId(productId);

        ProductWithReviews productWithReviews = ProductWithReviews.builder()
                .product(product)
                .reviews(reviews)
                .build();
        productWithReviews.calculateAverageRating();
        return productWithReviews;
    }

    public List<ProductWithReviews> getAllProductsWithReviews() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    List<ReviewDTO> reviews = reviewClient.getReviewsByProductId(product.getId());
                    ProductWithReviews productWithReviews = ProductWithReviews.builder()
                            .product(product)
                            .reviews(reviews)
                            .build();
                    productWithReviews.calculateAverageRating();
                    return productWithReviews;
                }).toList();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
