package com.fcul.grouper.rest.resources.meeting;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Meeting;
import com.fcul.grouper.rest.controller.MeetingController;

public class MeetingsResource extends RepresentationModel<MeetingsResource> {

	private Long workgroupId;

	public MeetingsResource() {
		super();
	}

	public MeetingsResource(Long workgroupId) {
		super();
		this.workgroupId = workgroupId;
	}

	public void addMeetings(final Set<Meeting> meetings) {

		int meetingIndex = 1;
		for (Meeting meeting : meetings) {
			add(linkTo(methodOn(MeetingController.class).get(meeting.getId())).withRel("Meeting " + meetingIndex++));
		}

	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
