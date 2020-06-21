package com.fcul.grouper.rest.resources.course;

import java.io.Serializable;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.rest.controller.InstituitionController;
import com.fcul.grouper.rest.controller.LectiveCourseController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


public class CourseResource extends RepresentationModel<CourseResource> implements Serializable {

	private static final long serialVersionUID = -7226854599089886282L;

	private Long id;

	private String name;

	public CourseResource(Long id, String name) {
		this.id = id;
		this.name = name;
		
		
	}

	public CourseResource(final Course course) {

		this.setId(course.getId());
		this.setName(course.getName());
		
		Instituition instituition = course.getInstituition();
		
		if(instituition != null) {
			add(linkTo(methodOn(InstituitionController.class).get(instituition.getId())).withRel("instituition"));
		}
		
		Set<LectiveCourse> lectiveCourse = course.getLectiveCourses();
		
		if(!lectiveCourse.isEmpty()) {
			add(linkTo(methodOn(LectiveCourseController.class).getLectiveCoursesByCourse(course.getId())).withRel("lectiveCourses"));
		}
		
		

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
