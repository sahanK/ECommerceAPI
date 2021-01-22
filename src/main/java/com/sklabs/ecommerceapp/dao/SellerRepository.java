package com.sklabs.ecommerceapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sklabs.ecommerceapp.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

}
