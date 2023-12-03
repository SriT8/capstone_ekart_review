package com.walmart.ecartReviews.repository;


import com.walmart.ecartReviews.model.NewComment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NewCommentRepository extends MongoRepository<NewComment, String> {
    // Add custom query methods if needed
    @Query("{'productId' : ?0 }")
    List<NewComment> findByproductId(Integer productId);


    // Custom query to delete by productId and userId
    @Query("{ 'productId' : ?0, 'comments.user.userId' : ?1 }")
    List<NewComment> findByProductIdAndCommentsUserUserId(int productId, String userId);
    // Custom method to delete by productId and userId
    // Custom method to delete by productId and userId
    void deleteByProductIdAndCommentsUserUserId(int productId, String userId);

}
