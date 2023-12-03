package com.walmart.ecartReviews.controller;
import com.walmart.ecartReviews.EcartReviewsApplication;
import com.walmart.ecartReviews.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.walmart.ecartReviews.service.ReviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/product")
public class ReviewController {


    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public List<ReviewSearch> getAllreviews() {
        return reviewService.getAllreviews();
    }
    
    @GetMapping("/reviewSearchId/{reviewSearchId}")
    public List<ReviewSearch> getByReviewSearchId(@PathVariable int reviewSearchId) {
        return reviewService.findByReviewSearchId(reviewSearchId);
    }

    @GetMapping("/{product_id}/by-ratings/{rating}")
    public Object getCommentsForGivenRating(@PathVariable int product_id, @PathVariable int rating) {
        return reviewService.getCommentsForGivenRating(product_id,rating);
    }

    @PostMapping("/{product_id}/comment/")
    public Object addNewComments(@PathVariable int product_id, @RequestBody Comment comment,@RequestHeader Map<String,String> headers){
        logger.info(""+headers);

        if(headers.get("approval-status")==null || !headers.get("approval-status").toLowerCase( ).equals("approved") ){
            logger.error("===Comment not added to DB as the Review is not approved ===== ");
            return "Review is not yet approved ";
        }


        if(headers.get("user-id-email")!=null)
        return reviewService.addComments(product_id,comment,headers.get("user-id-email"));
        else {
            logger.error("==== No user email id details found in the header===== ");

            return "No user details found";
        }
    }

    @GetMapping("/by-average-ratings/{averageRatings}")
    public List<ReviewSearch> getProductsByAverageRatings(@PathVariable double averageRatings) {
        return reviewService.getProductsByAverageRatings(averageRatings);
    }

    @GetMapping("/{id}")
    public Optional<ReviewSearch> getById(@PathVariable String id) {
        return reviewService.findById(id);
    }

    @GetMapping("/high-ratings/{averageRatings}")
    public List<ReviewSearch> getProductsWithHigherRatings(@PathVariable double averageRatings) {
        return reviewService.getProductsWithHigherRatings(averageRatings);
    }   
    
    @PostMapping("/{reviewSearchId}/comments")
    public ReviewSearch addCommentToReview(
        @PathVariable int reviewSearchId, 
        @RequestBody Comment comment) {
        return reviewService.addCommentToReview(reviewSearchId, comment);
    }
    
    /*
    @PostMapping("/{reviewId}/comments")
    public ReviewSearch addCommentToReview(
            @PathVariable String reviewId,
            @RequestBody Comment comment) {
        // Find the review by ID
        ReviewSearch review = reviewService.findById(reviewId).orElse(null);

        if (review != null) {
            // Add the new comment to the review
            Ratings ratings = review.getRatings();
            if (ratings == null) {
                ratings = new Ratings();
                review.setRatings(ratings);
            }

            List<Comment> comments = ratings.getComments();
            if (comments == null) {
                comments = new ArrayList<>();
                ratings.setComments(comments);
            }

            Comment newComment = new Comment(Comment.getuserId(), Comment.getComment(), Comment.getRate());
            comments.add(newComment);

            // Save the updated review back to the database
            return reviewService.save(review);
        } else {
            // Handle the case where the review with the given ID is not found
            return null;
        }
    }
         */
}
