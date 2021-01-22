package com.sklabs.ecommerceapp.service;

import java.util.List;

import com.sklabs.ecommerceapp.entity.Seller;

public interface SellerService {
	public List<Seller> findAll();
	
	public Seller findById(int id);
	
	public Seller save(Seller seller);
	
	public void deleteSeller(Seller seller);
}
