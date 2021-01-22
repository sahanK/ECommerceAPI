package com.sklabs.ecommerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sklabs.ecommerceapp.dto.BuyerDTO;
import com.sklabs.ecommerceapp.entity.Buyer;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;
import com.sklabs.ecommerceapp.service.BuyerService;

@RestController
@RequestMapping("/buyers")
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Buyer> findAll() {
		return buyerService.findAll();
	}
	
	@GetMapping("/{buyerId}")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER','SELLER','USER')")
	public Buyer  findById(@PathVariable int buyerId) {
		Buyer buyer = buyerService.findById(buyerId);
		if(buyer == null) {
			throw new ResourceNotFoundException("No buyer found with the id of "+buyerId);
		}
		return buyer;
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public Buyer saveBuyer(@RequestBody BuyerDTO buyerDto) {
		Buyer buyer = new Buyer();
		
		buyer.setFirstName(buyerDto.getFirstName());
		buyer.setLastName(buyerDto.getLastName());
		buyer.setAddress(buyerDto.getAddress());
		buyer.setEmail(buyerDto.getEmail());
		buyer.setGender(buyerDto.getGender());
		buyer.setId(0);
		
		buyerService.save(buyer);
		return buyer;
	}
	
	@PutMapping("/{buyerId}")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public Buyer updateBuyer(@PathVariable int buyerId, @RequestBody BuyerDTO newBuyer) {
		Buyer buyer = buyerService.findById(buyerId);
		if(buyer == null) {
			throw new ResourceNotFoundException("No buyer found with the id of "+buyerId);
		}
		buyer.setFirstName(newBuyer.getFirstName());
		buyer.setLastName(newBuyer.getLastName());
		buyer.setAddress(newBuyer.getAddress());
		buyer.setEmail(newBuyer.getEmail());
		buyer.setGender(newBuyer.getGender());
		
		buyerService.save(buyer);
		return buyer;
	}
	
	@DeleteMapping("/{buyerId}")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public String deleteBuyer(@PathVariable int buyerId) {
		Buyer buyer = buyerService.findById(buyerId);
		if(buyer == null) {
			throw new ResourceNotFoundException("No buyer found with id of "+buyerId);
		}
		buyerService.deleteById(buyerId);
		return "Buyer deleted with the id of "+ buyerId;
	}
		
}
