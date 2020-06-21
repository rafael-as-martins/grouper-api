package com.fcul.grouper.rest.resources.professor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.rest.controller.CourseController;

public class ProfessorCoursesResource extends RepresentationModel<ProfessorCoursesResource> {

	private String year;

	private Long professorId;

	public ProfessorCoursesResource(final String year, final Long professorId) {

		this.setYear(year);
		this.setProfessorId(professorId);

	}

	public void addLinks(final Set<Course> courses) {
		int courseIndex = 1;
		for (Course course : courses) {
			add(linkTo(methodOn(CourseController.class).get(course.getId())).withRel("Course " + courseIndex++));
		}
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
