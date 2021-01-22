package com.sklabs.ecommerceapp.dto;

public class FeedbackDTO {
	private String feedback;
	private int rating;
	private int buyerId;
	
	public FeedbackDTO() {
		
	}

	public FeedbackDTO(String feedback, int rating, int buyerId) {
		this.feedback = feedback;
		this.rating = rating;
		this.buyerId = buyerId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

}
