package com.fcul.grouper.rest.resources.student;

import java.text.ParseException;
import java.util.Date;

import com.fcul.grouper.utils.DateConverterUtil;

public class StepUpdateEndDateResource {

	private Long projectId;

	private Long stepOrder;

	private Date endDate;

	public StepUpdateEndDateResource(Long projectId, String endDate, Long stepOrder) throws ParseException {
		super();
		this.projectId = projectId;
		this.stepOrder = stepOrder;
		this.endDate = DateConverterUtil.extractDate(endDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Long stepOrder) {
		this.stepOrder = stepOrder;
	}

}
