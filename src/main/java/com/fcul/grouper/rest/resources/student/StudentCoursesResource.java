package com.fcul.grouper.rest.resources.student;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.rest.controller.CourseController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StudentCoursesResource extends RepresentationModel<StudentCoursesResource> {

	private Long studentId;

	private String year;

	public StudentCoursesResource(Long studentId, String year) {
		super();
		this.studentId = studentId;
		this.year = year;
	}

	public void addCourses(final Set<Course> courses) {

		
		if (!courses.isEmpty()) {

			int courseIndex = 1;
			for (Course course : courses) {
				add(linkTo(methodOn(CourseController.class).get(course.getId())).withRel("Course " + courseIndex++));
			}

		}

	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
