package com.fcul.grouper.rest.resources.project;

import java.text.ParseException;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.utils.DateConverterUtil;

public class ProjectUpdateEndDateResource extends RepresentationModel<ProjectUpdateEndDateResource>{

	private Long projectId;

	private Date endDate;

	public ProjectUpdateEndDateResource() {
		super();
	}

	public ProjectUpdateEndDateResource(Long projectId, String endDate) throws ParseException {
		super();
		this.projectId = projectId;
		this.endDate = DateConverterUtil.extractDate(endDate);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
