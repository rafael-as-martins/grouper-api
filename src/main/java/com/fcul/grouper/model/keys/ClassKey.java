package com.fcul.grouper.model.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClassKey implements Serializable {

	private static final long serialVersionUID = -2294841810539205846L;

	@Column(name = "course")
	private Long course;

	@Column(name = "name")
	private String name;

	@Column(name = "year")
	private String year;

	public ClassKey() {
	}

	public ClassKey(Long course, String name, String year) {
		this.course = course;
		this.name = name;
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		ClassKey other = (ClassKey) obj;

		if (course == null) {
			if (other.getCourse() != null) {
				return false;
			}
		} else if (!course.equals(other.getCourse())) {
			return false;
		}

		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}

		if (year == null) {
			if (other.getYear() != null) {
				return false;
			}
		} else if (!year.equals(other.getYear())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());

		return result;
	}

	public Long getCourse() {
		return course;
	}

	public void setCourse(Long course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
