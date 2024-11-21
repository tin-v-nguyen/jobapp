package com.tin.jobapp.review.service;

import com.tin.jobapp.company.model.Company;
import com.tin.jobapp.company.service.CompanyService;
import com.tin.jobapp.review.model.Review;
import com.tin.jobapp.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getCompanyReviews(Long companyId) {
        if (companyService.findCompanyById(companyId) == null) return null;
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review postReview(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company == null) {
            return null;
        }
        review.setCompany(company);
        return reviewRepository.save(review);
    }

    @Override
    public Review getCompanyReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Review putReviewById(Long companyId, Long reviewId, Review review) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Review foundReview = reviews.stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
        if (foundReview == null) return null;
        foundReview.setDescription(review.getDescription());
        foundReview.setTitle(review.getTitle());
        foundReview.setRating(review.getRating());
        return reviewRepository.save(foundReview);
    }

    @Override
    public Boolean deleteReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Review foundReview = reviews.stream().filter(r -> r.getId().equals(reviewId)).findFirst().orElse(null);
        if (foundReview == null) return false;
        Company company = foundReview.getCompany();
        company.getReviews().remove(foundReview);
        companyService.updateCompany(companyId, company);
        reviewRepository.deleteById(reviewId);
        return true;
    }
}
