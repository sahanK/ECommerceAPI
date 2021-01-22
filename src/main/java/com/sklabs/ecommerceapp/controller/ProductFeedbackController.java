package com.sklabs.ecommerceapp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sklabs.ecommerceapp.dto.FeedbackDTO;
import com.sklabs.ecommerceapp.entity.Buyer;
import com.sklabs.ecommerceapp.entity.Product;
import com.sklabs.ecommerceapp.entity.ProductFeedback;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;
import com.sklabs.ecommerceapp.service.BuyerService;
import com.sklabs.ecommerceapp.service.ProductFeedbackService;
import com.sklabs.ecommerceapp.service.ProductService;

@RestController
public class ProductFeedbackController {
	@Autowired
	private ProductFeedbackService productFeedbackService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("buyers/{buyerId}/feedbacks")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public List<ProductFeedback> getAllfeedbacksFromBuyer(@PathVariable int buyerId){
		List<ProductFeedback> feedbacks = productFeedbackService.findByBuyerId(buyerId);
		if(feedbacks.isEmpty()) {
			throw new ResourceNotFoundException("No feedbacks from the buyer with id of "+ buyerId);
		}
		return feedbacks;
	}
	
	@GetMapping("products/{productId}/feedbacks")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN','BUYER','USER')")
	public List<ProductFeedback> getAllFeedbacksForProduct(@PathVariable int productId){
		List<ProductFeedback> feedbacks = productFeedbackService.findByProductId(productId);
		if(feedbacks.isEmpty()) {
			throw new ResourceNotFoundException("No feedbacks for the product with id of "+ productId);
		}
		return feedbacks;
	}
	
	@PostMapping("/products/{productId}/feedbacks")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public ProductFeedback addFeedback(@PathVariable int productId, @RequestBody FeedbackDTO feedbackDto) {
		Buyer buyer = buyerService.findById(feedbackDto.getBuyerId());
		Product product = productService.findById(productId);
		
		if(buyer == null) {
			throw new ResourceNotFoundException("No buyer found with id of "+ feedbackDto.getBuyerId());
		}
		if(product == null) {
			throw new ResourceNotFoundException("No product found with id of "+ productId);
		}
		
		ProductFeedback feedback = new ProductFeedback();
		
		feedback.setBuyer(buyer);
		feedback.setProduct(product);
		feedback.setFeedback(feedbackDto.getFeedback());
		feedback.setRating(feedbackDto.getRating());
		feedback.setDate(Date.valueOf(LocalDate.now()));
		
		productFeedbackService.save(feedback);
		return feedback;
	}
	
	@PutMapping("/feedbacks/{feedbackId}")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public ProductFeedback updateFeedback(@PathVariable int feedbackId, @RequestBody FeedbackDTO newFeedback) {
		ProductFeedback feedback = productFeedbackService.findById(feedbackId);
		if(feedback == null) {
			throw new ResourceNotFoundException("No feedback found with the id of "+ feedbackId);
		}
		feedback.setFeedback(newFeedback.getFeedback());
		feedback.setRating(newFeedback.getRating());
		productFeedbackService.save(feedback);
		return feedback;
	}
	
	@DeleteMapping("/feedbacks/{feedbackId}")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER','SELLER')")
	public String deleteFeedback(@PathVariable int feedbackId) {
		ProductFeedback feedback = productFeedbackService.findById(feedbackId);
		if(feedback == null) {
			throw new ResourceNotFoundException("No feedback found with the id of "+ feedbackId);
		}
		productFeedbackService.deleteFeedback(feedback);
		return "Feedback deleted with the id of "+ feedbackId;
	}
	
}
