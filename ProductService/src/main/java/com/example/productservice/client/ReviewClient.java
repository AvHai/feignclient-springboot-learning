package com.example.productservice.client;

import com.example.productservice.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ReviewService", url = "http://localhost:8081" )
public interface ReviewClient {

    @GetMapping("/api/v1/reviews/product/{productId}")
    List<ReviewDTO> getReviewsByProductId(@PathVariable("productId") Long productId);
}
