package com.fcul.grouper.rest.resources;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Country;

public class CountryResource extends RepresentationModel<CountryResource> implements Serializable {

	private static final long serialVersionUID = 4612862883650241464L;

	private long id;
	
	private String name;
	
	
	public CountryResource(final long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public CountryResource(final Country country) {
		id = country.getId();
		name = country.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
