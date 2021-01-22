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
import org.springframework.web.bind.annotation.RestController;

import com.sklabs.ecommerceapp.dto.ProductDTO;
import com.sklabs.ecommerceapp.entity.Product;
import com.sklabs.ecommerceapp.entity.Seller;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;
import com.sklabs.ecommerceapp.service.ProductService;
import com.sklabs.ecommerceapp.service.SellerService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/products")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN','BUYER','USER')")
	public List<Product> getAllProducts(){
		return productService.findAll();
	}
	
	@GetMapping("/sellers/{sellerId}/products")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN','BUYER','USER')")
	public List<Product> getAllProductsForSeller(@PathVariable int sellerId){
		return productService.findBySellerId(sellerId);
	}
	
	@GetMapping("/sellers/{sellerId}/products/{productId}")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN','BUYER','USER')")
	public Product getProductOfSeller(@PathVariable int sellerId, @PathVariable int productId) {
		Product product = productService.findByIdAndSellerId(productId, sellerId);
		if(product == null) {
			throw new ResourceNotFoundException("No product found with the id of "+ productId);
		}
		return product;
	}
	
	@PostMapping("/sellers/{sellerId}/products")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	public Product saveProduct(@PathVariable int sellerId, @RequestBody ProductDTO newProduct) {
		Product product = new Product();
		
		product.setName(newProduct.getName());
		product.setDescription(newProduct.getDescription());
		product.setPrice(newProduct.getPrice());
		product.setQuantity(newProduct.getQuantity());
		
		Seller seller = sellerService.findById(sellerId);
		
		if(seller == null) {
			throw new ResourceNotFoundException("No seller found with id of "+ sellerId);
		}
		product.setSeller(seller);
		productService.save(product);
		return product;
	}
	
	@PutMapping("/sellers/{sellerId}/products/{productId}")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	public Product updateProductOfSeller(@PathVariable int sellerId, @PathVariable int productId, @RequestBody ProductDTO newProduct) {
		Product product = productService.findByIdAndSellerId(productId, sellerId);
		
		if(product == null) {
			throw new ResourceNotFoundException("No product found with the id of "+ productId);
		}
		
		product.setName(newProduct.getName());
		product.setDescription(newProduct.getDescription());
		product.setPrice(newProduct.getPrice());
		product.setQuantity(newProduct.getQuantity());
		
		productService.save(product);
		return product;
	}
	
	@DeleteMapping("/sellers/{sellerId}/products/{productId}")
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	public String deleteProductOfSeller(@PathVariable int sellerId, @PathVariable int productId) {
		Product product = productService.findByIdAndSellerId(productId, sellerId);
		if(product == null) {
			throw new ResourceNotFoundException("No product found with the id of "+ productId);
		}
		productService.deleteProduct(product);
		return "Product deleted with the id of "+ productId;
	}
}
