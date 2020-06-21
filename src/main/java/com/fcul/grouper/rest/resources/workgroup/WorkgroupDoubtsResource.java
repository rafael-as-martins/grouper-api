package com.fcul.grouper.rest.resources.workgroup;

import com.fcul.grouper.rest.resources.doubt.MultipleDoubtsResource;

public class WorkgroupDoubtsResource extends MultipleDoubtsResource {

	private Long workgroupId;

	public WorkgroupDoubtsResource() {
	}

	public WorkgroupDoubtsResource(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
