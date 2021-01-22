package com.sklabs.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sklabs.ecommerceapp.dao.ProductRepository;
import com.sklabs.ecommerceapp.dao.SellerRepository;
import com.sklabs.ecommerceapp.entity.Product;
import com.sklabs.ecommerceapp.entity.Seller;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SellerRepository sellerRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = null;
		if(result.isPresent()) {
			product = result.get();
		}
		return product;
	}

	@Override
	public Product save(Product product) {
		productRepository.save(product);
		return null;
	}

	@Override
	public void deleteById(int id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findBySellerId(int sellerId) {
		Optional<Seller> result = sellerRepository.findById(sellerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No seller found with the id of "+ sellerId);
		}
		List<Product> products = productRepository.findBySellerId(sellerId);
		return products;
	}

	@Override
	public Product findByIdAndSellerId(int id, int sellerId) {
		Optional<Seller> result = sellerRepository.findById(sellerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No seller found with the id of "+ sellerId);
		}		
		Product product = productRepository.findByIdAndSellerId(id, sellerId);
		return product;
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

}
