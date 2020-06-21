package com.fcul.grouper.model.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LectiveCourseKey implements Serializable {

	private static final long serialVersionUID = 9128635551130096286L;

	@Column(name = "professor")
	private Long professor;

	@Column(name = "course")
	private Long course;

	@Column(name = "year")
	private String year;

	public LectiveCourseKey() {
	}

	public LectiveCourseKey(final long student, final long course, final String year) {
		this.professor = student;
		this.course = course;
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

		LectiveCourseKey other = (LectiveCourseKey) obj;

		if (professor == null) {
			if (other.professor != null) {
				return false;
			}
		} else if (!professor.equals(other.professor)) {
			return false;
		}

		if (course == null) {
			if (other.course != null) {
				return false;
			}
		} else if (!course.equals(other.course)) {
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

		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());

		return result;
	}

	public Long getCourse() {
		return course;
	}

	public void setCourse(Long course) {
		this.course = course;
	}

	public Long getProfessor() {
		return professor;
	}

	public void setProfessor(Long professor) {
		this.professor = professor;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
