package com.aston.colomb.services;

import com.aston.colomb.dao.ReviewRepository;
import com.aston.colomb.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(Review compte) {
        return reviewRepository.save(compte);
    }

    public Optional<Review> findReviewById(Integer id) {
        return reviewRepository.findById(id);
    }
}
