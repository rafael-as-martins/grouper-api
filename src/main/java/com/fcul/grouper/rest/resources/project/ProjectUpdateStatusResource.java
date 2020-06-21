package com.fcul.grouper.rest.resources.project;

public class ProjectUpdateStatusResource {

	private long projectId;

	private boolean status;

	public ProjectUpdateStatusResource() {
		super();
	}

	public ProjectUpdateStatusResource(long projectId, boolean status) {
		super();
		this.projectId = projectId;
		this.status = status;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
