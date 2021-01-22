package com.sklabs.ecommerceapp.service;

import java.util.List;

import com.sklabs.ecommerceapp.entity.ProductFeedback;

public interface ProductFeedbackService {
	public ProductFeedback save(ProductFeedback feedback);
	
	public ProductFeedback findById(int id);
	
	public List<ProductFeedback> findByBuyerId(int buyerId);
	
	public List<ProductFeedback> findByProductId(int productId);
	
	public ProductFeedback findByIdAndBuyerId(int id, int buyerId);
	
	public ProductFeedback findByIdAndProductId(int id, int productId);
	
	public void deleteFeedback(ProductFeedback feedback);
}
