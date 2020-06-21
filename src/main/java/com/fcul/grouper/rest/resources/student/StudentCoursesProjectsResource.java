package com.fcul.grouper.rest.resources.student;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Project;
import com.fcul.grouper.rest.controller.ProjectController;

public class StudentCoursesProjectsResource extends RepresentationModel<StudentCoursesResource>{

	private String year;
	
	private Long studentId;
	
	private Long courseId;

	public StudentCoursesProjectsResource(String year, Long studentId, Long courseId) {
		super();
		this.year = year;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	
	
	public void addProjects(final Set<Project> projects) {
		
		int projectIndex = 1;
		for(Project project : projects) {
			add(linkTo(methodOn(ProjectController.class).get(project.getId())).withRel("Project " + projectIndex++));
		}
		
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
	
	
	
}
