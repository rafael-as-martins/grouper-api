package com.fcul.grouper.rest.resources.workgroup;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.rest.controller.MessageController;

public class WorkgroupMessagesResource extends RepresentationModel<WorkgroupMessagesResource> {

	private Long workgroupId;

	public WorkgroupMessagesResource() {

	}

	public WorkgroupMessagesResource(Long workgroupId) {
		super();
		this.workgroupId = workgroupId;
	}

	public void addMessages(Set<Long> messages) {

		int messageIndex = 1;
		for (Long message : messages) {
			add(linkTo(methodOn(MessageController.class).get(message)).withRel("Message " + messageIndex++));
		}

	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
