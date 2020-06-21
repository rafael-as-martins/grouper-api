package com.fcul.grouper.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fcul.grouper.model.Login;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 6535178259314346478L;

	private Collection<? extends GrantedAuthority> authorities;

	private long id;

	private String name;

	private String email;

	private String password;

	public UserPrincipal(int id, String firstName, String lastName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = new StringBuilder().append(firstName).append(" ").append(lastName).toString();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(Login user) {

		List<GrantedAuthority> authorities = new ArrayList<>();

		String role = new StringBuilder().append("ROLE_").append(user.getUserType()).toString();

		authorities.add(new SimpleGrantedAuthority(role));

		return new UserPrincipal(user.getId().intValue(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword(), authorities);

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
