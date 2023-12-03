package com.walmart.ecartReviews.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Annotations
@Data

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product_collection")

//Class
public class ReviewSearch 
{
	@Id
	private String id;
	@Field("id")
	private int reviewSearchId;
    public int getReviewSearchId() {
		return reviewSearchId;
	}
	public void setReviewSearchId(int reviewSearchId) {
		this.reviewSearchId = reviewSearchId;
	}
	private String title;
	private String productName;
	private Ratings ratings;
   
	/*private Ratings ratings;
	 * 
	 */
    
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the title
	 */
	public String getid() {
		return id;
	}
	/**
	 * @param title the title to set
	 */
	public void setid(String id) {
		this.id = id;
	}
	/**
	 * @return the ratings
	 */
	public Ratings getRatings() {
		return ratings;
	}

	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}

	

}


