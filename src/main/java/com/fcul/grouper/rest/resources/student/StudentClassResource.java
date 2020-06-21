package com.fcul.grouper.rest.resources.student;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Class;;

public class StudentClassResource extends RepresentationModel<StudentClassResource> {

	private Long courseId;

	private Long studentId;

	private String className;

	private String year;

	public StudentClassResource(Long courseId, Long studentId) {
		super();
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public StudentClassResource() {
		super();
	}

	public void addClass(final Class studentClass) {

		this.className = studentClass.getName();
		this.year = studentClass.getYear();

	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
