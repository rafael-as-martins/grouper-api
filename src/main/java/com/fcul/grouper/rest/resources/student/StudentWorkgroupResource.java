package com.fcul.grouper.rest.resources.student;

import org.springframework.hateoas.RepresentationModel;

public class StudentWorkgroupResource extends RepresentationModel<StudentWorkgroupResource> {

	private long studentId;

	private long workgroupId;

	public StudentWorkgroupResource(long studentId, long workgroupId) {
		super();
		this.studentId = studentId;
		this.workgroupId = workgroupId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
