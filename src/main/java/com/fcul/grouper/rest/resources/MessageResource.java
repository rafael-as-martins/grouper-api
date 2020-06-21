package com.fcul.grouper.rest.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Message;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.MessageController;
import com.fcul.grouper.rest.controller.StudentsController;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class MessageResource extends RepresentationModel<MessageResource> {

	private Long id;

	private String content;

	private Date creationDate;

	private Long answerTo;
	
	private Long studentId;
	
	private Long workgroupId;

	public MessageResource(Long id, String content, Date creationDate, Long answerTo, Long studentId, Long workgroupId) {
		super();
		this.id = id;
		this.content = content;
		this.creationDate = creationDate;
		this.answerTo = answerTo;
		this.studentId = studentId;
		this.workgroupId = workgroupId;

	}

	public MessageResource(final Message message) {

		id = message.getId();
		content = message.getContent();
		studentId = (message.getStudent() != null)? message.getStudent().getId(): null;
		workgroupId = (message.getWorkgroup() != null)? message.getWorkgroup().getId() : null;
		creationDate = message.getCreationDate();
		
		
		if(message.getAnswerTo() != null) {
			this.answerTo = message.getAnswerTo().getId();
		}
		
		Student student = message.getStudent();
		
		if(student != null) {
			add(linkTo(methodOn(StudentsController.class).get(student.getId())).withRel("Student"));
		}
		
		Workgroup workgroup = message.getWorkgroup();
		
		if(workgroup != null) {
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel("Workgroup"));
		}
		
		Set<Message> answers = message.getAnswers();

		if (!answers.isEmpty()) {

			int messageIndex = 1;
			for (Message answer : answers) {
				add(linkTo(methodOn(MessageController.class).get(answer.getId())).withRel("Answer (" + (messageIndex++) + ")"));
			}

		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getAnswerTo() {
		return answerTo;
	}

	public void setAnswerTo(Long answerTo) {
		this.answerTo = answerTo;
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

}
