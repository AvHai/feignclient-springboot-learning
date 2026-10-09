package com.example.productservice.controller;

import com.example.productservice.dto.ProductWithReviews;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> response = productService.getAllProducts();

        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}/with-reviews")
    public ResponseEntity<ProductWithReviews> getProductWithReviews(@PathVariable("id") Long productId) {
        ProductWithReviews response = productService.getProductWithReviews(productId);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/with-reviews")
    public ResponseEntity<List<ProductWithReviews>> getAllProductsWithReviews() {
        return ResponseEntity.ok(productService.getAllProductsWithReviews());
    }

    @PostMapping("/create")
    public ResponseEntity<Product>createProduct(@RequestBody Product product) {
            Product createdProduct = productService.createProduct(product);
            return  ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }
}
