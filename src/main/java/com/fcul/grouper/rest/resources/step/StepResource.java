package com.fcul.grouper.rest.resources.step;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.ParseException;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Step;
import com.fcul.grouper.rest.controller.ProjectController;
import com.fcul.grouper.utils.DateConverterUtil;

public class StepResource extends RepresentationModel<StepResource> {

	@NonNull
	private Long projectId;

	@NonNull
	private String name;

	@NonNull
	private String objetives;

	@NonNull
	private Date startDate;

	@NonNull
	private Date endDate;
	
	private Long stepOrder;

	public StepResource(Long stepOrder, String name, String objetives, String startDate, String endDate,
			Long projectId) {
		super();

		this.stepOrder = stepOrder;
		this.name = name;
		this.objetives = objetives;
		this.projectId = projectId;

		try {
			this.startDate = DateConverterUtil.extractDate(startDate);
			this.endDate = DateConverterUtil.extractDate(endDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public StepResource(final Step step) {

		stepOrder = step.getStepOrder();
		name = step.getName();
		objetives = step.getObjetives();
		startDate = step.getStartDate();
		endDate = step.getEndDate();
		projectId = step.getProject().getId();

		Project project = step.getProject();

		if (project != null) {
			add(linkTo(methodOn(ProjectController.class).get(project.getId())).withRel("Project"));
		}

	}

	public long getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Long stepOrder) {
		this.stepOrder = stepOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjetives() {
		return objetives;
	}

	public void setObjetives(String objetives) {
		this.objetives = objetives;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

}
