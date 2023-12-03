package com.walmart.ecartReviews.service;

import com.walmart.ecartReviews.model.Product;
import com.walmart.ecartReviews.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService 
{

	@Autowired
	private ProductRepository productRepository;

	//To add the review
	/*
	 * public Product addReview(Product prd) { return productRepository.save(prd); }
	 */
	
	public List<Product> findProductAll()
	{
		return productRepository.findAll();
	}
	
	public Product getProductById(int productId)
	{
		return (Product) productRepository.findById(productId);
	}
	
	
}
