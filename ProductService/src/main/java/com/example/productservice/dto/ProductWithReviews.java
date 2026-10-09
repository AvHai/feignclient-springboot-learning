package com.example.productservice.dto;

import com.example.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductWithReviews {
    private Product product;
    private List<ReviewDTO> reviews;
    private Integer totalReviews;
    private Double averageRating;

    // helper method to calculate averageRating

    public void calculateAverageRating() {
        if(reviews != null && !reviews.isEmpty()){
            this.averageRating = reviews.stream()
                    .mapToInt(ReviewDTO::getRating)
                    .average()
                    .orElse(0.0);
            this.totalReviews = reviews.size();
        }
        else {
            this.averageRating = 0.0;
            this.totalReviews = 0;
        }
    }
}
