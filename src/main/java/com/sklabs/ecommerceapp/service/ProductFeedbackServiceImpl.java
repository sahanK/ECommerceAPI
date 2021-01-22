package com.sklabs.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sklabs.ecommerceapp.dao.BuyerRepository;
import com.sklabs.ecommerceapp.dao.ProductFeedbackRepository;
import com.sklabs.ecommerceapp.dao.ProductRepository;
import com.sklabs.ecommerceapp.entity.Buyer;
import com.sklabs.ecommerceapp.entity.Product;
import com.sklabs.ecommerceapp.entity.ProductFeedback;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;

@Service
public class ProductFeedbackServiceImpl implements ProductFeedbackService {
	@Autowired
	private ProductFeedbackRepository productFeedbackRepository;
	@Autowired
	private BuyerRepository buyerRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductFeedback save(ProductFeedback feedback) {
		productFeedbackRepository.save(feedback);
		return feedback;
	}

	@Override
	public List<ProductFeedback> findByBuyerId(int buyerId) {
		Optional<Buyer> result = buyerRepository.findById(buyerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No buyer found with the id of "+ buyerId);
		}
		List<ProductFeedback> feedbacks = productFeedbackRepository.findByBuyerId(buyerId);
		return feedbacks;
	}

	@Override
	public List<ProductFeedback> findByProductId(int productId) {
		Optional<Product> result = productRepository.findById(productId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No product found with the id of "+ productId);
		}
		List<ProductFeedback> feedbacks = productFeedbackRepository.findByProductId(productId);
		return feedbacks;
	}

	@Override
	public ProductFeedback findByIdAndBuyerId(int id, int buyerId) {
		Optional<Buyer> result = buyerRepository.findById(buyerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No buyer found with the id of "+ buyerId);
		}
		ProductFeedback feedback = productFeedbackRepository.findByIdAndBuyerId(id, buyerId);
		return feedback;
	}

	@Override
	public ProductFeedback findByIdAndProductId(int id, int productId) {
		Optional<Product> result = productRepository.findById(productId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No product found with the id of "+ productId);
		}
		ProductFeedback feedback = productFeedbackRepository.findByIdAndProductId(id, productId);
		return feedback;
	}

	@Override
	public void deleteFeedback(ProductFeedback feedback) {
		productFeedbackRepository.delete(feedback);
	}

	@Override
	public ProductFeedback findById(int id) {
		Optional<ProductFeedback> result = productFeedbackRepository.findById(id);
		ProductFeedback feedback = null;
		if(result.isPresent()) {
			feedback = result.get();
		}
		return feedback;
	}
}
