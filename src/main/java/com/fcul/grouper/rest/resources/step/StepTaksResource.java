package com.fcul.grouper.rest.resources.step;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Task;
import com.fcul.grouper.rest.controller.TaskController;

public class StepTaksResource extends RepresentationModel<StepResource> {

	private Long projectId;

	private Long stepOrder;

	public StepTaksResource(Long projectId, Long stepOrder) {
		super();
		this.projectId = projectId;
		this.stepOrder = stepOrder;
	}

	public void addTasks(Set<Task> tasks) {

		int taskIndex = 1;
		
		StringBuilder relBuilder = new StringBuilder();
		for(Task task : tasks) {
			
			String rel = relBuilder.append("Task (").append(taskIndex++).append(")").toString();
			add(linkTo(methodOn(TaskController.class).getTask(task.getId())).withRel(rel));
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
