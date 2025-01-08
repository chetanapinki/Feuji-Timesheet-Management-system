package com.skillset.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.skillset.entity.UserLoginEntity;
import com.skillset.repository.UserLoginRepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{

	@Autowired
	private UserLoginRepo repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserLoginEntity> userlogin = repository.findByUserEmail(email);
		return userlogin.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + email));

	}
	
}