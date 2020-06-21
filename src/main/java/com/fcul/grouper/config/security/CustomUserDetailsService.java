package com.fcul.grouper.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcul.grouper.model.Login;
import com.fcul.grouper.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Login userCredentials = loginRepository.findByEmail(email);

		return UserPrincipal.create(userCredentials);

	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserByEmail(String email) {

		Login userCredentials = loginRepository.findByEmail(email);

		return UserPrincipal.create(userCredentials);
	}

}
