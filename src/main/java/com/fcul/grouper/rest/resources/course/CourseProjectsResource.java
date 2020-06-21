package com.fcul.grouper.rest.resources.course;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Project;
import com.fcul.grouper.rest.controller.ProjectController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CourseProjectsResource extends RepresentationModel<CourseProjectsResource> {

	private Long courseId;

	private String year;

	public CourseProjectsResource(Long courseId, String year) {
		super();
		this.courseId = courseId;
		this.year = year;
	}

	public void addProjects(final Set<Project> projects) {

		int projectIndex = 1;

		StringBuilder sb = new StringBuilder();

		for (Project project : projects) {
			String rel = sb.append("Project (").append(projectIndex++).append(")").toString();
			
			add(linkTo(methodOn(ProjectController.class).get(project.getId())).withRel(rel));
			sb.setLength(0);
		}

	}

	public void addProjects() {

	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
