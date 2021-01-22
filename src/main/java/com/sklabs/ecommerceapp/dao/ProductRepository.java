package com.sklabs.ecommerceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sklabs.ecommerceapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findBySellerId(int sellerId);
	
	public Product findByIdAndSellerId(int id, int sellerId);
}
