package com.fcul.grouper.rest.resources;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Feedback;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.model.types.FeedbackType;
import com.fcul.grouper.rest.controller.CourseController;
import com.fcul.grouper.rest.controller.ProfessorController;
import com.fcul.grouper.rest.controller.StepController;
import com.fcul.grouper.rest.controller.WorkgroupController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;;

public class FeedbackResource extends RepresentationModel<FeedbackResource> implements Serializable {

	private static final long serialVersionUID = -3089189116891063234L;

	@NonNull
	private Long workgroupId;

	@NonNull
	private Long professorId;

	@NonNull
	private Long stepOrder;

	@NonNull
	private Long projectId;

	@NonNull
	private FeedbackType type;

	@NonNull
	private String content;

	@NonNull
	private Double grade;

	private Long id;

	public FeedbackResource(Long id, String type, Double grade, String content, Long workgroupId, Long professorId,
			Long courseId, Long stepOrder, Long projectId) {
		super();
		this.id = id;
		this.grade = grade;
		this.content = content;
		this.workgroupId = workgroupId;
		this.professorId = professorId;
		this.stepOrder = stepOrder;
		this.projectId = projectId;
		this.type = FeedbackType.valueOf(type);
	}

	public FeedbackResource(final Feedback feedback) {

		this.id = feedback.getId();
		this.type = feedback.getType();
		this.grade = feedback.getGrade();
		this.content = feedback.getContent();

		Professor professor = feedback.getProfessor();

		if (professor != null) {
			this.professorId = professor.getId();
			add(linkTo(methodOn(ProfessorController.class).get(professor.getId())).withRel("professor"));
		}

		Workgroup workgroup = feedback.getWorkgroup();

		if (workgroup != null) {
			this.workgroupId = workgroup.getId();
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel("workgroup"));
		}

		Course course = feedback.getCourse();

		if (course != null) {
			add(linkTo(methodOn(CourseController.class).get(course.getId())).withRel("course"));
		}

		Step step = feedback.getStep();

		if (step != null) {
			this.stepOrder = step.getStepOrder();
			this.projectId = step.getId().getProject();

			add(linkTo(methodOn(StepController.class).get(this.stepOrder, this.professorId)).withRel("Step"));

		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Long getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Long stepOrder) {
		this.stepOrder = stepOrder;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public FeedbackType getType() {
		return type;
	}

	public void setType(FeedbackType type) {
		this.type = type;
	}

}
