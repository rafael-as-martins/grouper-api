package com.fcul.grouper.rest.resources.project;

import com.fcul.grouper.rest.resources.doubt.MultipleDoubtsResource;;

public class ProjectDoubtsResource extends MultipleDoubtsResource {

	private Long projectId;

	public ProjectDoubtsResource(Long projectId) {
		super();
		this.projectId = projectId;
	}

	public ProjectDoubtsResource() {
		super();
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
