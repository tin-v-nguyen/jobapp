package com.tin.jobapp.review.service;

import com.tin.jobapp.review.model.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getCompanyReviews(Long companyId);
    Review postReview(Long companyId, Review review);
    Review getCompanyReviewById(Long companyId, Long reviewId);
    Review putReviewById(Long companyId, Long reviewId, Review review);
    Boolean deleteReviewById(Long companyId, Long reviewId);
}
