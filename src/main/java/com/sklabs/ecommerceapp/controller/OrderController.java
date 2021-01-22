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
import org.springframework.web.bind.annotation.RestController;

import com.sklabs.ecommerceapp.dto.OrderDTO;
import com.sklabs.ecommerceapp.entity.Buyer;
import com.sklabs.ecommerceapp.entity.ProductOrder;
import com.sklabs.ecommerceapp.entity.Product;
import com.sklabs.ecommerceapp.entity.Seller;
import com.sklabs.ecommerceapp.exception.ResourceNotFoundException;
import com.sklabs.ecommerceapp.service.BuyerService;
import com.sklabs.ecommerceapp.service.OrderService;
import com.sklabs.ecommerceapp.service.ProductService;
import com.sklabs.ecommerceapp.service.SellerService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/sellers/{sellerId}/orders")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER')")
	public List<ProductOrder> getOrdersOfSeller(@PathVariable int sellerId){
		List<ProductOrder> orders = orderService.findBySellerId(sellerId);
		if(orders.isEmpty()) {
			throw new ResourceNotFoundException("No orders for the seller with id of "+ sellerId);
		}
		return orders;
	}
	
	@GetMapping("/sellers/{sellerId}/orders/{orderId}")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER')")
	public ProductOrder getOrderOfSeller(@PathVariable int sellerId, @PathVariable int orderId){
		ProductOrder order = orderService.findByIdAndSellerId(orderId, sellerId);
		if(order == null) {
			throw new ResourceNotFoundException("No order for the seller with id of "+ sellerId + " and order id "+ orderId);
		}
		return order;
	}
	
	@GetMapping("/buyers/{buyerId}/orders")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
	public List<ProductOrder> getOrdersOfBuyer(@PathVariable int buyerId){
		List<ProductOrder> orders = orderService.findByBuyerId(buyerId);
		if(orders.isEmpty()) {
			throw new ResourceNotFoundException("No orders from the buyer with id of "+ buyerId);
		}
		return orders;
	}
	
	@GetMapping("/buyers/{buyerId}/orders/{orderId}")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
	public ProductOrder getOrderOfBuyer(@PathVariable int buyerId, @PathVariable int orderId){
		ProductOrder order = orderService.findByIdAndBuyerId(orderId, buyerId);
		if(order == null) {
			throw new ResourceNotFoundException("No order from the buyer with id of "+ buyerId + " and order id "+ orderId);
		}
		return order;
	}
	
	@PostMapping("/orders")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public ProductOrder addOrder(@RequestBody OrderDTO newOrder) {
		ProductOrder order = new ProductOrder();
		
		order.setDescription(newOrder.getDescription());
		order.setQuantity(newOrder.getQuantity());
		order.setDate(Date.valueOf(LocalDate.now()));
		order.setConfirmed("not");
		
		int sellerId = newOrder.getSellerId();
		int productId = newOrder.getProductId();
		int buyerId = newOrder.getBuyerId();
		
		Seller seller = sellerService.findById(sellerId);
		Buyer buyer = buyerService.findById(buyerId);
		Product product = productService.findById(productId);
		
		if(seller == null) {
			throw new ResourceNotFoundException("No seller found with the id of "+ sellerId);
		}
		if(buyer == null) {
			throw new ResourceNotFoundException("No buyer found with the id of "+ sellerId);
		}
		if(product == null) {
			throw new ResourceNotFoundException("No product found with the id of "+ sellerId);
		}
		
		order.setSeller(seller);
		order.setBuyer(buyer);
		order.setProduct(product);
		
		orderService.save(order);
		return order;
	}
	
	@PutMapping("/orders/{orderId}")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
	public ProductOrder updateOrder(@PathVariable int orderId, @RequestBody OrderDTO newOrder) {
		ProductOrder order = orderService.findById(orderId);
		if(order == null) {
			throw new ResourceNotFoundException("No order found with the id of "+ orderId);
		}
		if(order.getConfirmed().equals("not"))
		{
			if(newOrder.getConfirmed().equals("yes")) {
				Product product = productService.findById(newOrder.getProductId());
				if(product.getQuantity() <= 0) {
					throw new ResourceNotFoundException("Stocks empty! Cannot proceed");
				}
				else {
					int currentQty = product.getQuantity();
					product.setQuantity(currentQty - newOrder.getQuantity());
					productService.save(product);
				}
			}
			order.setDescription(newOrder.getDescription());
			order.setQuantity(newOrder.getQuantity());
			order.setConfirmed(newOrder.getConfirmed());
		}
		else {
			throw new RuntimeException("Cannot proceed! Order is already confirmed.");
		}
		orderService.save(order);
		return order;
	}
	
	@DeleteMapping("/orders/{orderId}")
	@PreAuthorize("hasAnyRole('ADMIN','BUYER')")
	public String deleteOrder(@PathVariable int orderId) {
		ProductOrder order = orderService.findById(orderId);
		if(order == null) {
			throw new ResourceNotFoundException("No order found with the id of "+ orderId);
		}
		if(order.getConfirmed().equals("yes")) {
			return "Order is confirmed! Cannot delete now";
		}
		else {
			orderService.delete(order);
			return "Order deleted with the id of "+ orderId;
		}
	}
}
