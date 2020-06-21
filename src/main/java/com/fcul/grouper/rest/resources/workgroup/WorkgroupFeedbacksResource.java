package com.fcul.grouper.rest.resources.workgroup;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Feedback;
import com.fcul.grouper.rest.controller.FeedbackController;

public class WorkgroupFeedbacksResource extends RepresentationModel<WorkgroupFeedbacksResource> {

	private Long workgroupId;

	public WorkgroupFeedbacksResource() {
		super();
	}

	public WorkgroupFeedbacksResource(Long workgroupId) {
		super();
		this.workgroupId = workgroupId;
	}

	public void addFeedbacks(final Set<Feedback> feedbacks) {

		int feedbackIndex = 1;
		for (Feedback feedback : feedbacks) {
			add(linkTo(methodOn(FeedbackController.class).getFeedback(feedback.getId())).withRel("Feedback " + feedbackIndex++));
		}

	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
