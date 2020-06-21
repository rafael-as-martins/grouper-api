package com.fcul.grouper.rest.resources.workgroup;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.ClassController;
import com.fcul.grouper.rest.controller.ProjectController;
import com.fcul.grouper.rest.controller.StudentsController;

public class WorkgroupResource extends RepresentationModel<WorkgroupResource> {

	private final Long id;

	public WorkgroupResource(Long id) {
		super();
		this.id = id;
	}

	public WorkgroupResource(final Workgroup workgroup) {
		this.id = workgroup.getId();

		Project project = workgroup.getProject();

		if (project != null) {
			add(linkTo(methodOn(ProjectController.class).get(project.getId()))
					.withRel("project( " + project.getName() + " )"));
		}

		Class workgroupClass = workgroup.getWorkgroupClass();

		if (workgroupClass != null) {

			String year = workgroupClass.getYear();
			Long courseId = workgroupClass.getCourseId();
			String name = workgroupClass.getName();

			add(linkTo(methodOn(ClassController.class).get(year, courseId, name)).withRel("Class"));
		}

		Set<Student> students = workgroup.getStudents();

		if (!students.isEmpty()) {
			int elemNum = 1;
			for (Student student : students) {

				add(linkTo(methodOn(StudentsController.class).get(student.getId()))
						.withRel("Student elem(" + (elemNum++) + ")"));
			}
		}

	}

	public Long getId() {
		return id;
	}

}
