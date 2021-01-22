package com.sklabs.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sklabs.ecommerceapp.dao.BuyerRepository;
import com.sklabs.ecommerceapp.dao.OrderRepository;
import com.sklabs.ecommerceapp.dao.SellerRepository;
import com.sklabs.ecommerceapp.entity.Buyer;
import com.sklabs.ecommerceapp.entity.ProductOrder;
import com.sklabs.ecommerceapp.entity.Seller;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private SellerRepository sellerRepository;
	@Autowired
	private BuyerRepository buyerRepository;

	@Override
	public ProductOrder save(ProductOrder order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public void delete(ProductOrder order) {
		orderRepository.delete(order);
	}

	@Override
	public List<ProductOrder> findBySellerId(int sellerId) {
		Optional<Seller> result = sellerRepository.findById(sellerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No seller found with id of "+ sellerId);
		}
		List<ProductOrder> orders = orderRepository.findBySellerId(sellerId);
		return orders;
	}

	@Override
	public List<ProductOrder> findByBuyerId(int buyerId) {
		Optional<Buyer> result = buyerRepository.findById(buyerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No buyer found with id of "+ buyerId);
		}
		List<ProductOrder> orders = orderRepository.findByBuyerId(buyerId);
		return orders;
	}

	@Override
	public ProductOrder findByIdAndSellerId(int id, int sellerId) {
		Optional<Seller> result = sellerRepository.findById(sellerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No seller found with id of "+ sellerId);
		}
		ProductOrder order = orderRepository.findByIdAndSellerId(id, sellerId);
		return order;
	}

	@Override
	public ProductOrder findByIdAndBuyerId(int id, int buyerId) {
		Optional<Buyer> result = buyerRepository.findById(buyerId);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No buyer found with id of "+ buyerId);
		}
		ProductOrder order = orderRepository.findByIdAndBuyerId(id, buyerId);
		return order;
	}

	@Override
	public ProductOrder findById(int id) {
		Optional<ProductOrder> result = orderRepository.findById(id);
		ProductOrder order = null;
		if(result.isPresent()) {
			order = result.get();
		}
		return order;
	}

}
