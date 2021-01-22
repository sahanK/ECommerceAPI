package com.sklabs.ecommerceapp.dto;

public class OrderDTO {
	private String description;
	private int quantity;
	private String confirmed;
	private int sellerId;
	private int buyerId;
	private int productId;
	
	public OrderDTO() {
		
	}

	public OrderDTO(String description, int quantity, String confirmed, int sellerId, int buyerId, int productId) {
		this.description = description;
		this.quantity = quantity;
		this.confirmed = confirmed;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
