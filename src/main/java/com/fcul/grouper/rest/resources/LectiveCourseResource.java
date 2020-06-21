package com.fcul.grouper.rest.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.rest.controller.CourseController;
import com.fcul.grouper.rest.controller.ProfessorController;

public class LectiveCourseResource extends RepresentationModel<LectiveCourseResource> implements Serializable {

	private static final long serialVersionUID = 73608299970534735L;

	private String year;

	public LectiveCourseResource(final LectiveCourse lectiveCourse) {

		year = lectiveCourse.getYear();

		Course course = lectiveCourse.getCourse();
		Professor professor = lectiveCourse.getProfessor();

		if (course != null) {
			add(linkTo(methodOn(CourseController.class).get(course.getId())).withRel("course"));
		}

		if (professor != null) {
			add(linkTo(methodOn(ProfessorController.class).get(professor.getId())).withRel("professor"));
		}

	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public LectiveCourseResource(String year) {
		super();
		this.year = year;
	}

}
