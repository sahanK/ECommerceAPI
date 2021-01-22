package com.sklabs.ecommerceapp.service;

import java.util.List;

import com.sklabs.ecommerceapp.entity.Buyer;

public interface BuyerService {
	public List<Buyer> findAll();
	
	public Buyer findById(int id);
	
	public Buyer save(Buyer buyer);
	
	public void deleteById(int id);
}
