package com.fcul.grouper.rest.resources.staff;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.model.Staff;
import com.fcul.grouper.rest.controller.InstituitionController;

public class StaffResource extends RepresentationModel<StaffResource> implements Serializable {

	private static final long serialVersionUID = 2177445008820676675L;

	private Long id;

	private String firstName;

	private String lastName;

	private String fullName;

	private String email;

	public StaffResource(Long id, String firstName, String lastName, String fullName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.email = email;
	}

	public StaffResource(final Staff staff) {

		this.id = staff.getId();
		this.firstName = staff.getFirstName();
		this.lastName = staff.getLastName();
		this.email = staff.getEmail();

		Instituition instituition = staff.getInstituition();

		if (instituition != null) {
			add(linkTo(methodOn(InstituitionController.class).get(instituition.getId()))
					.withRel("instituition"));
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
