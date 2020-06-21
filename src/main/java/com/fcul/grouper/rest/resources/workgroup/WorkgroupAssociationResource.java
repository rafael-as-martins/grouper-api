package com.fcul.grouper.rest.resources.workgroup;

import org.springframework.hateoas.RepresentationModel;

public class WorkgroupAssociationResource extends RepresentationModel<WorkgroupAssociationResource> {

	private Long studentId;

	private Long workgroupId;

	private Long projectId;

	private Long courseId;

	private String year;

	private String className;

	public WorkgroupAssociationResource() {
		super();
	}

	public WorkgroupAssociationResource(Long studentId, Long workgroupId, Long projectId, Long courseId, String year,
			String className) {
		super();
		this.studentId = studentId;
		this.workgroupId = workgroupId;
		this.projectId = projectId;
		this.courseId = courseId;
		this.year = year;
		this.className = className;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
