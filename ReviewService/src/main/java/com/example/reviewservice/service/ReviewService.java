package com.example.reviewservice.service;

import com.example.reviewservice.entity.Review;
import com.example.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByProductId(Long productId){
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review createReview(Review review){
        return reviewRepository.save(review);
    }

}
