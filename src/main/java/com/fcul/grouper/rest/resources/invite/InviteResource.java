package com.fcul.grouper.rest.resources.invite;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Invite;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.StudentsController;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class InviteResource extends RepresentationModel<InviteResource> {

	private long id;

	private Date creationDate;

	private long issuingStudent;

	private long receiverStudent;

	private long workgroupId;

	public InviteResource(final Invite invite) {

		id = invite.getId();
		creationDate = invite.getCreationDate();

		Student issuingStudent = invite.getIssuingStudent();

		if (issuingStudent != null) {
			add(linkTo(methodOn(StudentsController.class).get(issuingStudent.getId())).withRel("issuingStudent"));
		}

		Student receiverStudent = invite.getReceiverStudent();

		if (receiverStudent != null) {
			add(linkTo(methodOn(StudentsController.class).get(receiverStudent.getId())).withRel("receiverStudent"));
		}

		Workgroup workgroup = invite.getWorkgroup();

		if (workgroup != null) {
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel("workgroup"));
		}

	}

	public InviteResource(long id, Date creationDate) {
		super();
		this.id = id;
		this.creationDate = creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIssuingStudent() {
		return issuingStudent;
	}

	public void setIssuingStudent(long issuingStudent) {
		this.issuingStudent = issuingStudent;
	}

	public long getReceiverStudent() {
		return receiverStudent;
	}

	public void setReceiverStudent(long receiverStudent) {
		this.receiverStudent = receiverStudent;
	}

	public long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
