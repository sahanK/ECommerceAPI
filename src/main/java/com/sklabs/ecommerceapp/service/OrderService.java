package com.sklabs.ecommerceapp.service;

import java.util.List;

import com.sklabs.ecommerceapp.entity.ProductOrder;

public interface OrderService {
	public ProductOrder findById(int id);
	
	public ProductOrder save(ProductOrder order);
	
	public void delete(ProductOrder order);
	
	public List<ProductOrder> findBySellerId(int sellerId);
	
	public List<ProductOrder> findByBuyerId(int buyerId);
	
	public ProductOrder findByIdAndSellerId(int id, int sellerId);
	
	public ProductOrder findByIdAndBuyerId(int id, int buyerId);
	
}
