package com.sklabs.ecommerceapp.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "seller")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private int id;
	
	@Column(name = "seller_name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "opened_date")
	private Date openedDate;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Product> products;
	
	@OneToMany(mappedBy = "seller",fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	@JsonBackReference
	private List<ProductOrder> orders;
	
	public Seller() {
		
	}

	public Seller(String name, String email, String address, Date date) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.openedDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return openedDate;
	}

	public void setDate(Date date) {
		this.openedDate = date;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ProductOrder> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", date=" + openedDate
				+ "]";
	}
	
	// add convenience method to add product
	public void addProduct(Product product) {
		if(products == null) {
			products = new ArrayList<Product>();
		}
		products.add(product);
		product.setSeller(this);
	}
	
	// add convenience method to add order
	public void addOrder(ProductOrder order) {
		if(orders == null) {
			orders = new ArrayList<ProductOrder>();
		}
		orders.add(order);
		order.setSeller(this);
	}
	
}
