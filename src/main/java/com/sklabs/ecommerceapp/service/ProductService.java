package com.sklabs.ecommerceapp.service;

import java.util.List;

import com.sklabs.ecommerceapp.entity.Product;

public interface ProductService {
	public List<Product> findAll();
	
	public Product findById(int id);
	
	public Product save(Product product);
	
	public void deleteById(int id);
	
	public List<Product> findBySellerId(int sellerId);
	
	public Product findByIdAndSellerId(int id, int sellerId);
	
	public void deleteProduct(Product product);
}
