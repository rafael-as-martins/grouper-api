package com.fcul.grouper.rest.resources.meeting;

import java.text.ParseException;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Meeting;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.WorkgroupController;
import com.fcul.grouper.utils.DateConverterUtil;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class MeetingResource extends RepresentationModel<MeetingResource> {

	private Long id;

	private Date dateTime;

	private Integer duration;

	private Long workgroupId;

	public MeetingResource(Long id, String dateTime, Integer duration, Long workgroupId) throws ParseException {
		super();
		this.id = id;
		this.dateTime = DateConverterUtil.extractDate(dateTime);
		this.duration = duration;
		this.workgroupId = workgroupId;
	}

	public MeetingResource(final Meeting meeting) {

		id = meeting.getId();
		dateTime = meeting.getDateTime();
		duration = meeting.getDuration();

		Workgroup workgroup = meeting.getWorkgroup();
		workgroupId = workgroup.getId();

		if (workgroup != null) {
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel("Workgroup"));
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
