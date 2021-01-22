package com.sklabs.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sklabs.ecommerceapp.dao.BuyerRepository;
import com.sklabs.ecommerceapp.entity.Buyer;

@Service
public class BuyerServiceImpl implements BuyerService {
	@Autowired
	private BuyerRepository buyerRepository;

	@Override
	public List<Buyer> findAll() {
		return buyerRepository.findAll();
	}

	@Override
	public Buyer findById(int id) {
		Optional<Buyer> result = buyerRepository.findById(id);
		Buyer buyer = null;
		if(result.isPresent()) {
			buyer = result.get();
		}
		return buyer;
	}

	@Override
	public Buyer save(Buyer buyer) {
		buyerRepository.save(buyer);
		return buyer;
	}

	@Override
	public void deleteById(int id) {
		buyerRepository.deleteById(id);
	}

}
