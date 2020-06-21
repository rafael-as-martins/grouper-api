package com.fcul.grouper.rest.resources.assessment;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Assessment;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.StudentsController;
import com.fcul.grouper.rest.controller.WorkgroupController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssessmentResource extends RepresentationModel<AssessmentResource> {

	private Long issuerId;

	private Long receiverId;

	private Long workgroupId;

	private Double grade;

	public AssessmentResource(Long issuerId, Long receiverId, Long workgroupId, Double grade) {
		super();
		this.issuerId = issuerId;
		this.receiverId = receiverId;
		this.workgroupId = workgroupId;
		this.grade = grade;
	}

	public void updateResource(final Assessment assessment) {

		grade = assessment.getGrade();

		Student issuingStudent = assessment.getIssuingStudent();

		if (issuingStudent != null) {
			add(linkTo(methodOn(StudentsController.class).get(issuingStudent.getId())).withRel("issuingStudent"));
		}

		Student receiverStudent = assessment.getReceiverStudent();

		if (receiverStudent != null) {
			add(linkTo(methodOn(StudentsController.class).get(receiverStudent.getId())).withRel("receiverStudent"));
		}

		Workgroup workgroup = assessment.getWorkgroup();

		if (workgroup != null) {
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel("workgroup"));
		}

	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
