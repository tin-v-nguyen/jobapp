package com.tin.jobapp.review.controller;

import com.tin.jobapp.review.model.Review;
import com.tin.jobapp.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        ResponseEntity<List<Review>> response;
        List<Review> reviews = reviewService.getCompanyReviews(companyId);
        response = reviews == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(reviews, HttpStatus.OK);
        return response;
    }

    @PostMapping("/review")
    public ResponseEntity<Review> postReview(@PathVariable Long companyId,
                                             @RequestBody Review review) {
        Review newReview = reviewService.postReview(companyId, review);
        ResponseEntity<Review> response;
        response = newReview == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(newReview, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        ResponseEntity<Review> response;
        Review foundReview = reviewService.getCompanyReviewById(companyId, reviewId);
        response = foundReview != null ? new ResponseEntity<>(foundReview, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Review> putReviewById(@PathVariable Long companyId,
                                                @PathVariable Long reviewId,
                                                @RequestBody Review review) {
        ResponseEntity<Review> response;
        Review foundReview = reviewService.putReviewById(companyId, reviewId, review);
        response = foundReview != null ? new ResponseEntity<>(foundReview, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Boolean deleted = reviewService.deleteReviewById(companyId, reviewId);
        if (deleted) return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
