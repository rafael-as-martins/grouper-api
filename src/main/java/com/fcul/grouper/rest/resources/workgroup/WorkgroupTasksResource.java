package com.fcul.grouper.rest.resources.workgroup;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Task;
import com.fcul.grouper.rest.controller.TaskController;

public class WorkgroupTasksResource extends RepresentationModel<WorkgroupTasksResource> {

	private Long workgroupId;

	public WorkgroupTasksResource() {
		super();
	}

	public WorkgroupTasksResource(Long workgroupId) {
		super();
		this.workgroupId = workgroupId;
	}

	public void addTasks(final Set<Task> tasks) {

		int taskIndex = 1;
		for (Task task : tasks) {
			add(linkTo(methodOn(TaskController.class).getTask(task.getId())).withRel("Task " + taskIndex++));
		}

	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
