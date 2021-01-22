package com.sklabs.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sklabs.ecommerceapp.dao.SellerRepository;
import com.sklabs.ecommerceapp.entity.Seller;

@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public List<Seller> findAll() {
		return sellerRepository.findAll();
	}

	@Override
	public Seller findById(int id) {
		Optional<Seller> result = sellerRepository.findById(id);
		Seller seller = null;
		if(result.isPresent()) {
			seller = result.get();
		}
		return seller;
	}

	@Override
	public Seller save(Seller seller) {
		sellerRepository.save(seller);
		return seller;
	}

	@Override
	public void deleteSeller(Seller seller) {
		sellerRepository.delete(seller);
	}

}
