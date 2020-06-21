package com.fcul.grouper.rest.resources.project;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.rest.controller.StepController;

public class ProjectStepsResource extends RepresentationModel<ProjectStepsResource> {

	private Long projectId;

	public ProjectStepsResource(Long projectId) {
		super();
		this.projectId = projectId;
	}

	public ProjectStepsResource() {
		super();
	}

	public void addStepsLinks(final Set<Step> steps) {

		int stepIndex = 1;
		for (Step step : steps) {
			add(linkTo(methodOn(StepController.class).get(step.getStepOrder(), step.getProject().getId()))
					.withRel("Step " + stepIndex++));
		}

	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
