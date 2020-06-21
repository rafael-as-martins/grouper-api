package com.fcul.grouper.rest.resources;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Configuration;

public class ConfigurationResource extends RepresentationModel<ConfigurationResource> implements Serializable {

	private static final long serialVersionUID = -4681355715693321420L;

	private Long id;

	private String property;

	private String value;

	public ConfigurationResource(Long id, String property, String value) {
		super();
		this.id = id;
		this.property = property;
		this.value = value;
	}

	public ConfigurationResource(final Configuration confiuration) {

		this.id = confiuration.getId();
		this.property = confiuration.getProperty();
		this.value = confiuration.getValue();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
