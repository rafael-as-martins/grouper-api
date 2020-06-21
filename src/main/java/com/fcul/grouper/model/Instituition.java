package com.fcul.grouper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "instituition")
public class Instituition implements Serializable {

	private static final long serialVersionUID = -3582239227646906596L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	@NonNull
	@Size(max = 255)
	private String name;

	@Column(name = "address")
	@NonNull
	@Size(max = 255)
	private String address;
	
	@ManyToOne(targetEntity = Country.class)
	@JoinColumn(insertable = true, updatable = true, name = "country", referencedColumnName = "id", nullable = false)
	@NonNull
	private Country country;

	@Column(name = "phone")
	@NonNull
	@Size(max = 20)
	private String phone;

	public Instituition() {
	}

	public Instituition(String name, String addresss, Country country, String phone) {
		this.name = name;
		this.address = addresss;
		this.country = country;
		this.phone = phone;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
