package com.fcul.grouper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "grouper.storage")
public class StorageProperties {

	private String location;

	private boolean db;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isDb() {
		return db;
	}

	public void setDb(boolean db) {
		this.db = db;
	}

}
