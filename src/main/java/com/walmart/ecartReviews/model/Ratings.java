package com.walmart.ecartReviews.model;
import java.util.List;
import java.util.Map;
public class Ratings {
   
    private double averageRatings;
    private int numberOfReviews;
    private List<Comment> comments;
    
	/**
	 * @return the averageRatings
	 */
	public double getAverageRatings() {
		return averageRatings;
	}
	/**
	 * @param averageRatings the averageRatings to set
	 */
	public void setAverageRatings(double averageRatings) {
		this.averageRatings = averageRatings;
	}
	/**
	 * @return the numberOfReviews
	 */
	public int getNumberOfReviews() {
		return numberOfReviews;
	}
	/**
	 * @param numberOfReviews the numberOfReviews to set
	 */
	public void setNumberOfReviews(int numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}
	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	

}