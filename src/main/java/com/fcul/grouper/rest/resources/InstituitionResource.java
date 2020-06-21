package com.fcul.grouper.rest.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Country;
import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.rest.controller.CountryController;

public class InstituitionResource extends RepresentationModel<InstituitionResource> implements Serializable {

	private static final long serialVersionUID = 8427487904694186757L;

	private Long id;

	private String name;

	private String address;

	private String phone;

	public InstituitionResource(Long id, String name, String address, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public InstituitionResource(final Instituition instituition) {

		this.id = instituition.getId();
		this.name = instituition.getName();
		this.address = instituition.getAddress();
		this.phone = instituition.getPhone();

		Country country = instituition.getCountry();
		
		if(country != null) {
			add(linkTo(methodOn(CountryController.class).get(country.getId())).withRel("Country"));
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
