package com.sklabs.ecommerceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sklabs.ecommerceapp.entity.ProductOrder;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Integer> {
	public List<ProductOrder> findBySellerId(int sellerId);
	
	public List<ProductOrder> findByBuyerId(int buyerId);
	
	public List<ProductOrder> findByProductId(int productId);
	
	public ProductOrder findByIdAndSellerId(int id, int sellerId);
	
	public ProductOrder findByIdAndBuyerId(int id, int buyerId);
	
	public ProductOrder findByIdAndProductId(int id, int productId);
}
