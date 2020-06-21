package com.fcul.grouper.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.config.security.JwtTokenProvider;
import com.fcul.grouper.config.security.UserPrincipal;
import com.fcul.grouper.rest.resources.JwtAuthenticationResponse;
import com.fcul.grouper.rest.resources.LoginRequest;
import com.fcul.grouper.rest.resources.SignedInResource;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api", produces = "application/json")
public class AuthenticationController {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/auth", method = { RequestMethod.POST })
	public ResponseEntity<Object> signinUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String jwt = tokenProvider.generateToken(authentication);

		return ResponseEntity.status(HttpStatus.OK).body(new SignedInResource(new JwtAuthenticationResponse(jwt),
				user.getAuthorities(), user.getName(), user.getId()));
	}

}
