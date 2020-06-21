package com.fcul.grouper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.types.UserType;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {

	private static final long serialVersionUID = 698099201392758820L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", nullable = false)
	@NonNull
	@Size(max = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NonNull
	@Size(max = 50)
	private String lastName;

	@Column(name = "email", unique = true, nullable = false)
	@NonNull
	@Size(max = 255)
	private String email;

	@Column(name = "password", unique = false)
	@NonNull
	@Size(max = 255)
	private String password;

	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	@NonNull
	private UserType userType;

	@ManyToOne(targetEntity = Instituition.class)
	@JoinColumn(insertable = false, updatable = false, nullable = false, name = "instituition", referencedColumnName = "id")
	private Instituition instituition;

	public Staff() {
	}

	public Staff(String firstname, String lastname, String email, Instituition instituition) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.instituition = instituition;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instituition getInstituition() {
		return instituition;
	}

	public void setInstituition(Instituition instituition) {
		this.instituition = instituition;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
