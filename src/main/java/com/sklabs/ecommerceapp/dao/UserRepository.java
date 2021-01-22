package com.sklabs.ecommerceapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sklabs.ecommerceapp.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
	public Optional<AppUser> findByUsername(String username);
}
