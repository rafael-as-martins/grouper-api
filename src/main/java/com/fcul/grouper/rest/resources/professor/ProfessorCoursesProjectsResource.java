package com.fcul.grouper.rest.resources.professor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Project;
import com.fcul.grouper.rest.controller.ProjectController;
import com.fcul.grouper.rest.resources.student.StudentCoursesResource;

public class ProfessorCoursesProjectsResource extends RepresentationModel<StudentCoursesResource> {

	private String year;

	private Long professorId;

	private Long courseId;

	public ProfessorCoursesProjectsResource(String year, Long professorId, Long courseId) {
		super();
		this.year = year;
		this.professorId = professorId;
		this.courseId = courseId;
	}

	public void addProjects(final Set<Project> projects) {

		int projectIndex = 1;
		for (Project project : projects) {
			add(linkTo(methodOn(ProjectController.class).get(project.getId())).withRel("Project " + projectIndex++));
		}

	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

}
