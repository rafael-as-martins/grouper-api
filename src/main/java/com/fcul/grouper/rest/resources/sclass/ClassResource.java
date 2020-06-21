package com.fcul.grouper.rest.resources.sclass;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.rest.controller.LectiveCourseController;

public class ClassResource extends RepresentationModel<ClassResource> implements Serializable {

	private static final long serialVersionUID = -2904574500595586838L;

	private String name;

	public ClassResource(String name) {
		this.name = name;
	}

	public ClassResource(final Class subject) {

		this.name = subject.getName();

		LectiveCourse lectiveCourse = subject.getLectiveCourse();

		if (lectiveCourse != null) {

			long professorId = lectiveCourse.getProfessor().getId();
			long courseId = lectiveCourse.getCourse().getId();

			add(linkTo(methodOn(LectiveCourseController.class).get(lectiveCourse.getYear(), courseId,
					professorId)).withRel("Lective Course"));
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
