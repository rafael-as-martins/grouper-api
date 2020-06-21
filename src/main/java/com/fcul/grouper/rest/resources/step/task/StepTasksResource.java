package com.fcul.grouper.rest.resources.step.task;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.rest.controller.StepTaskController;
import com.fcul.grouper.rest.resources.step.StepResource;

public class StepTasksResource extends RepresentationModel<StepResource> {

	private Long projectId;

	private Long stepOrder;

	public StepTasksResource() {
		super();
	}

	public StepTasksResource(Long projectId, Long stepOrder) {
		super();
		this.projectId = projectId;
		this.stepOrder = stepOrder;
	}

	public void addStepTasksLinks(final Set<StepTask> stepTasks) {

		int stepTaskIndex = 1;
		StringBuilder relBuilder = new StringBuilder();

		for (StepTask stepTask : stepTasks) {
			String rel = relBuilder.append("Step Task (").append(stepTaskIndex++).append(")").toString();
			add(linkTo(methodOn(StepTaskController.class).get(stepTask.getId())).withRel(rel));
			relBuilder.setLength(0);
		}
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
