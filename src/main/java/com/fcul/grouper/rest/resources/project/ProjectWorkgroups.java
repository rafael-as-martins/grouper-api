package com.fcul.grouper.rest.resources.project;

import com.fcul.grouper.rest.resources.workgroup.MutipleWorkgroupsResource;

public class ProjectWorkgroups extends MutipleWorkgroupsResource {

	private Long projectId;

	private String search;

	private String orderField;

	private Integer page;

	private Integer pageSize;

	public ProjectWorkgroups() {
		super();
	}

	public ProjectWorkgroups(Long projectId, String search, String orderField, Integer page, Integer pageSize) {
		super();
		this.projectId = projectId;
		this.search = search;
		this.orderField = orderField;
		this.page = page;
		this.pageSize = pageSize;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
