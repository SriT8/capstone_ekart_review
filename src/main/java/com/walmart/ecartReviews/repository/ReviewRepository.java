package com.walmart.ecartReviews.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.walmart.ecartReviews.model.ReviewSearch;
public interface ReviewRepository extends MongoRepository<ReviewSearch, String > {
	
    
	
	Optional<ReviewSearch> findById(String id);
	@Query("{'reviewSearchId' : ?0 }")
	List<ReviewSearch> findByReviewSearchId(Integer reviewSearchId);
	@Query("{'ratings.averageRatings' : ?0 }")
	List<ReviewSearch> findProductsByAverageRatings(double averageRatings);
	@Query("{'ratings.averageRatings': {$gte: ?0}}")
    List<ReviewSearch> findByAverageRatingsGreaterThan(double averageRatings);
	ReviewSearch save(List<ReviewSearch> existingReview);
	

}
	

	

