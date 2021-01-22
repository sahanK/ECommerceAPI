package com.sklabs.ecommerceapp.controller;

import java.sql.Date;
import java.time.LocalDate;
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

import com.sklabs.ecommerceapp.dto.SellerDTO;
import com.sklabs.ecommerceapp.entity.Seller;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;
import com.sklabs.ecommerceapp.service.SellerService;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN','BUYER','USER')")
	public List<Seller> findAll(){
		return sellerService.findAll();
	}
	
	@GetMapping("/{sellerId}")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN','BUYER','USER')")
	public Seller findById(@PathVariable int sellerId) {
		Seller seller = sellerService.findById(sellerId);
		if(seller == null) {
			throw new ResourceNotFoundException("No seller found with id of "+ sellerId);
		}
		return seller;
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	public Seller saveSeller(@RequestBody SellerDTO sellerDto) {
		Seller seller = new Seller();
		
		seller.setId(0);
		seller.setName(sellerDto.getName());
		seller.setAddress(sellerDto.getAddress());
		seller.setEmail(sellerDto.getEmail());
		seller.setDate(Date.valueOf(LocalDate.now()));
		
		sellerService.save(seller);
		return seller;
	}
	
	@PutMapping("/{sellerId}")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	public Seller updateSeller(@PathVariable int sellerId, @RequestBody SellerDTO newSeller) {
		Seller seller = sellerService.findById(sellerId);
		if(seller == null) {
			throw new ResourceNotFoundException("No seller found with id of "+ sellerId);
		}
		seller.setName(newSeller.getName());
		seller.setAddress(newSeller.getAddress());
		seller.setEmail(newSeller.getEmail());
		
		sellerService.save(seller);
		return seller;
	}
	
	@DeleteMapping("/{sellerId}")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	public String deleteSeller(@PathVariable int sellerId) {
		Seller seller = sellerService.findById(sellerId);
		if(seller == null) {
			throw new ResourceNotFoundException("No seller found with id of "+ sellerId);
		}
		sellerService.deleteSeller(seller);
		return "Seller deleted with the id of "+ sellerId;
	}
	
}
