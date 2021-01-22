package com.sklabs.ecommerceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sklabs.ecommerceapp.entity.ProductFeedback;

public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, Integer> {
	public List<ProductFeedback> findByBuyerId(int buyerId);
	
	public List<ProductFeedback> findByProductId(int productId);
	
	public ProductFeedback findByIdAndBuyerId(int id, int buyerId);
	
	public ProductFeedback findByIdAndProductId(int id, int productId);
}
