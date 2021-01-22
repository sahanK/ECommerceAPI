package com.sklabs.ecommerceapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sklabs.ecommerceapp.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

}
