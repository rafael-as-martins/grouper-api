package com.fcul.grouper.rest.resources.student;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Country;
import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.ClassController;
import com.fcul.grouper.rest.controller.CountryController;
import com.fcul.grouper.rest.controller.InstituitionController;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class StudentResource extends RepresentationModel<StudentResource> implements Serializable {

	private static final long serialVersionUID = 7972102868153010264L;

	private Long id;

	private String firstName;

	private String lastName;

	private String degree;

	private String number;

	private String email;

	private String encryptedNic;

	private String photoPath;

	private Long countryId;

	private Long instituitionId;

	private String password;

	public StudentResource(Long id, String firstName, String lastName, String degree, String number, String email,
			String password, String photo, String encryptedNic, Long countryId, Long instituitionId) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.degree = degree;
		this.number = number;
		this.email = email;
		this.password = password;
		this.encryptedNic = encryptedNic;
		this.photoPath = photo;
		this.countryId = countryId;
		this.instituitionId = instituitionId;
	}

	public StudentResource(final Student student) {

		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.degree = student.getDegree();
		this.number = student.getNumber();
		this.email = student.getEmail();
		this.photoPath = student.getPhotoPath();
		this.encryptedNic = student.getEncryptedNic();

		Instituition instituition = student.getInstituition();

		if (instituition != null) {
			instituitionId = instituition.getId();
			add(linkTo(methodOn(InstituitionController.class).get(instituition.getId())).withRel("instituiion"));
		}

		Set<Workgroup> workgroups = student.getWorkgroups();

		if (workgroups != null && !workgroups.isEmpty()) {
			add(linkTo(methodOn(WorkgroupController.class).getStudentWorkgroups(id)).withRel("workgroups"));
		}

		Country country = student.getCountryNic();

		if (country != null) {
			countryId = country.getId();
			add(linkTo(methodOn(CountryController.class).get(country.getId())).withRel("Country"));
		}

		Set<Class> classes = student.getClasses();

		if (!classes.isEmpty()) {
			for (Class studentClass : classes) {

				String year = studentClass.getYear();
				long courseId = studentClass.getCourseId();
				String name = studentClass.getName();

				add(linkTo(methodOn(ClassController.class).get(year, courseId, name))
						.withRel("Student Class (" + name + ")"));
			}
		}

	}

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDegree() {
		return degree;
	}

	public String getNumber() {
		return number;
	}

	public String getEmail() {
		return email;
	}

	public String getEncryptedNic() {
		return encryptedNic;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEncryptedNic(String encryptedNic) {
		this.encryptedNic = encryptedNic;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getInstituitionId() {
		return instituitionId;
	}

	public void setInstituitionId(Long instituitionId) {
		this.instituitionId = instituitionId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
