package com.walmart.ecartReviews.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.ecartReviews.model.Product;
import com.walmart.ecartReviews.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findProductAll()
	{
	  return productService.findProductAll();
	}
	 
	@GetMapping("/productId/{productId}")
	public Product getProductById(@PathVariable int productId)
	{
		return productService.getProductById(productId);
	}
	
}
