package com.aston.colomb.services;

import com.aston.colomb.dao.ReviewRepository;
import com.aston.colomb.entities.Review;
import com.aston.colomb.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(Review compte) {
        return reviewRepository.save(compte);
    }

    public Optional<Review> findReviewById(Integer id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (!reviewOptional.isPresent()) {
            throw new ReviewNotFoundException("L'avis que vous voulez accéder n'existe pas.");
        }
        return reviewOptional;
    }

//    public List<Review> getAllReviewsEntreprise(Integer id) {
//        return reviewRepository.findByCompte_Id(id);
//    }

    public List<Review> getAllReviewsReported() {
        return reviewRepository.findByEstSignale(true);
    }

    public void updateReviewEstSuspendu(Integer id, Review review) {
        Review reviewBdd = reviewRepository.findById(id).get();
        if (reviewBdd == null) {
            throw new ReviewNotFoundException("L'avis que vous voulez modifier n'existe pas.");
        }
        reviewBdd.setEstSignale(review.getEstSignale());
        reviewRepository.save(reviewBdd);
    }

    public void deleteReview(Integer id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (!reviewOptional.isPresent()) {
            throw new ReviewNotFoundException("L'avis que vous voulez supprimer n'existe pas.");
        }
        reviewRepository.delete(reviewOptional.get());
    }

    public Review editReview(Integer id, Review review) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (!reviewOptional.isPresent()) {
            throw new ReviewNotFoundException("L'avis que vous voulez modifier n'existe pas.");
        }
        Review reviewBdd = reviewOptional.get();
        reviewMapper.updateReview(review, reviewBdd);
        reviewRepository.save(reviewBdd);
        return reviewBdd;
    }
}
