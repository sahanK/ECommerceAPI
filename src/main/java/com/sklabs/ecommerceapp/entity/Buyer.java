package com.sklabs.ecommerceapp.entity;

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
@Table(name = "buyer")
public class Buyer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buyer_id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	@JsonBackReference
	private List<ProductOrder> orders;
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	@JsonBackReference
	private List<ProductFeedback> feedbacks;
	
	public Buyer() {
		
	}

	public Buyer(String firstName, String lastName, String gender, String email, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public List<ProductOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ProductOrder> orders) {
		this.orders = orders;
	}

	public List<ProductFeedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<ProductFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", address=" + address + "]";
	}
	
	// add convenience method to add order
	public void addOrder(ProductOrder order) {
		if(orders == null) {
			orders = new ArrayList<ProductOrder>();
		}
		orders.add(order);
		order.setBuyer(this);
	}
	
	// add convenience method to add feedback
	public void addFeedback(ProductFeedback feedback) {
		if(feedbacks == null) {
			feedbacks = new ArrayList<ProductFeedback>();
		}
		feedbacks.add(feedback);
		feedback.setBuyer(this);
	}
}
