package com.fcul.grouper.rest.resources.file;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.File;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.FileController;
import com.fcul.grouper.rest.controller.StudentsController;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class FileResource extends RepresentationModel<FileResource> {

	private long workgroupId;

	private long studentId;

	private long id;
	
	private String url;

	public FileResource(long workgroupId, long studentId, long id, String url) {
		super();
		this.workgroupId = workgroupId;
		this.studentId = studentId;
		this.id = id;
		this.url = url;
	}

	public FileResource(final File file) {
		
		this.id = file.getId();
		this.url = file.getUrl();
		

		Workgroup workgroup = file.getWorkgroup();

		if (workgroup != null) {
			this.workgroupId = workgroup.getId();
			add(linkTo(methodOn(WorkgroupController.class).get(workgroupId)).withRel("Workgroup"));
		}

		Student student = file.getStudent();

		if (student != null) {
			this.studentId = student.getId();
			add(linkTo(methodOn(StudentsController.class).get(studentId)).withRel("Student"));
		}

		add(linkTo(methodOn(FileController.class).getFileContent(id)).withRel("File Content"));

	}

	public long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
