package com.sklabs.ecommerceapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sklabs.ecommerceapp.config.MyUserDetails;
import com.sklabs.ecommerceapp.dao.UserRepository;
import com.sklabs.ecommerceapp.dto.UserDTO;
import com.sklabs.ecommerceapp.entity.AppUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				//new ArrayList<>());
		return user.map(MyUserDetails::new).get();
		
	}
	
	public AppUser save(UserDTO newUser) {
		AppUser user = new AppUser();
		user.setUsername(newUser.getUsername());
		user.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		user.setRoles(newUser.getRoles());
		user.setActive(newUser.getActive());
		return userRepository.save(user);
	}
}