package com.fcul.grouper.rest.resources.doubt;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.ParseException;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.StudentsController;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class DoubtResource extends RepresentationModel<DoubtResource> {

	private Long id;

	private String question;

	private Date creationDate;

	private String answer;

	private Date answerDate;

	private Long studentId;

	private Long workgroupId;

	public DoubtResource(String question, String answer, Long studentId, Long workgroupId) throws ParseException {
		super();
		this.question = question;
		this.answer = answer;
		this.studentId = studentId;
		this.workgroupId = workgroupId;
	}

	public DoubtResource(final Doubt doubt) {

		id = doubt.getId();
		question = doubt.getQuestion();
		creationDate = doubt.getCreationDate();
		answer = doubt.getAnswer();
		answerDate = doubt.getAnswerDate();

		Student student = doubt.getStudent();
		
		studentId = student.getId();
		
		if (student != null) {
			add(linkTo(methodOn(StudentsController.class).get(student.getId())).withRel("student"));
		}

		Workgroup workgroup = doubt.getWorkgroup();

		workgroupId = workgroup.getId();
		
		if (workgroup != null) {
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel("workgroup"));
		}

	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
