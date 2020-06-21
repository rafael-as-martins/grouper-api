package com.fcul.grouper.rest.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.StepController;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class TaskResource extends RepresentationModel<TaskResource> implements Serializable {

	private static final long serialVersionUID = 3283609029809424269L;

	private Long id;
	
	private Long workgroupId;

	private String content;

	private Boolean state;

	private Long projectId;

	private Long stepOrder;

	public TaskResource(Long id, String content, Boolean state, Long projectId, Long stepOrder, Long workgroupId) {
		super();
		this.id = id;
		this.content = content;
		this.state = state;
		this.projectId = projectId;
		this.stepOrder = stepOrder;
		this.workgroupId = workgroupId;
	}

	public TaskResource(final Task task) {

		this.id = task.getId();
		this.content = task.getContent();
		this.state = task.getState();

		Workgroup workgroup = task.getGroup();

		this.workgroupId = workgroup.getId();
		if (workgroup != null) {
			add(linkTo(methodOn(WorkgroupController.class).getProjectWorkgroups(workgroup.getProject().getId()))
					.withRel("Workgroup"));
		}

		Step step = task.getStep();

		this.stepOrder = step.getStepOrder();
		this.projectId = step.getProject().getId();
		
		if (step != null) {

			long order = step.getStepOrder();
			long projectId = step.getProject().getId();

			add(linkTo(methodOn(StepController.class).get(order, projectId)).withRel("Step"));
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
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

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
