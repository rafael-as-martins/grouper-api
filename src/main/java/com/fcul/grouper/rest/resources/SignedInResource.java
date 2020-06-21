package com.fcul.grouper.rest.resources;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class SignedInResource {

	private JwtAuthenticationResponse jwtAuthenticationResponse;

	private Collection<? extends GrantedAuthority> roles;

	private String name;
	
	private Long id;

	public SignedInResource(JwtAuthenticationResponse jwtAuthenticationResponse,
			Collection<? extends GrantedAuthority> roles, String name, Long id) {

		this.jwtAuthenticationResponse = jwtAuthenticationResponse;
		this.roles = roles;
		this.name = name;
		this.id = id;
	}

	public JwtAuthenticationResponse getJwtAuthenticationResponse() {
		return jwtAuthenticationResponse;
	}

	public void setJwtAuthenticationResponse(JwtAuthenticationResponse jwtAuthenticationResponse) {
		this.jwtAuthenticationResponse = jwtAuthenticationResponse;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
