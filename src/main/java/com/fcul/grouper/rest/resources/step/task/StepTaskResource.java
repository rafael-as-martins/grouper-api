package com.fcul.grouper.rest.resources.step.task;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.rest.controller.StepController;
import com.fcul.grouper.rest.resources.step.StepResource;

public class StepTaskResource extends RepresentationModel<StepResource> {

	private Long id;

	private Long projectId;

	private Long stepOrder;

	private String content;

	public StepTaskResource() {
		super();
	}

	public StepTaskResource(final StepTask stepTask) {

		this.id = stepTask.getId();
		this.content = stepTask.getContent();

		Step step = stepTask.getStep();

		if (step != null) {
			this.projectId = step.getId().getProject();
			this.stepOrder = step.getId().getStepOrder();
			add(linkTo(methodOn(StepController.class).get(stepOrder, projectId)).withRel("Step"));
		}

	}

	public StepTaskResource(Long projectId, Long stepOder, String content, Long id) {
		super();
		this.projectId = projectId;
		this.stepOrder = stepOder;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
