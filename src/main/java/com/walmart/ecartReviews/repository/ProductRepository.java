package com.walmart.ecartReviews.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.walmart.ecartReviews.model.Product;
import org.springframework.data.mongodb.repository.Query;


public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findById(int productId);

//	@Query("{'id':?0}")
//	List<Product> findByProductId(int productId);
}
