package com.fcul.grouper.rest.resources.professor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Country;
import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.rest.controller.CountryController;
import com.fcul.grouper.rest.controller.InstituitionController;
import com.fcul.grouper.rest.controller.LectiveCourseController;

public class ProfessorResource extends RepresentationModel<ProfessorResource> implements Serializable {

	private static final long serialVersionUID = -2986567184120141412L;

	private Long id;

	private String firstName;

	private String lastName;

	private String number;

	private String email;

	private String encryptedNic;

	private String photoPath;

	private String password;

	public ProfessorResource(Long id, String firstName, String lastName, String number, String email,
			String encryptedNic, String photo, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
		this.encryptedNic = encryptedNic;
		this.photoPath = photo;
		this.password = password;
	}

	public ProfessorResource(final Professor professor) {

		this.id = professor.getId();
		this.firstName = professor.getFirstName();
		this.lastName = professor.getLastName();
		this.number = professor.getNumber();
		this.email = professor.getEmail();
		this.encryptedNic = professor.getEncryptedNic();
		this.photoPath = professor.getPhotoPath();

		Instituition instituition = professor.getInstituition();

		if (instituition != null) {
			add(linkTo(methodOn(InstituitionController.class).get(instituition.getId())).withRel("Instituition"));
		}

		Country country = professor.getCountryNic();

		if (country != null) {
			add(linkTo(methodOn(CountryController.class).get(country.getId())).withRel("Country"));
		}

		Set<LectiveCourse> lectiveCourses = professor.getLectiveCourses();

		if (!lectiveCourses.isEmpty()) {

			for (LectiveCourse lectiveCourse : lectiveCourses) {

				String year = lectiveCourse.getYear();
				Long courseId = lectiveCourse.getCourse().getId();
				Long professorId = lectiveCourse.getProfessor().getId();

				add(linkTo(methodOn(LectiveCourseController.class).get(year, courseId, professorId))
						.withRel("Lective Course (" + lectiveCourse.getYear() + ")"));

			}

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedNic() {
		return encryptedNic;
	}

	public void setEncryptedNic(String encryptedNic) {
		this.encryptedNic = encryptedNic;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
