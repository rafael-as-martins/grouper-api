package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "doubt")
@NamedQueries({

		@NamedQuery(name = Doubt.FIND_BY_WORKGROUP_ID, query = "Select d from Doubt d JOIN d.workgroup w where w.id = :workgroupId"),
		@NamedQuery(name = Doubt.FIND_BY_PROJECT_ID, query = "Select d from Doubt d JOIN d.workgroup w JOIN w.project p where p.id = :projectId")

})
public class Doubt implements Serializable {

	private static final long serialVersionUID = -5630231906764629227L;

	public static final String FIND_BY_WORKGROUP_ID = "Doubt.findByWorkgroupId";

	public static final String FIND_BY_PROJECT_ID = "Doubt.findByProjectId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@Lob
	@Column(name = "question", nullable = false)
	private String question;

	@Column(name = "creation_date", nullable = false)
	@NonNull
	private Date creationDate;

	@Column(name = "answer")
	@NonNull
	@Lob
	private String answer;

	@Column(name = "answer_date")
	private Date answerDate;

	@ManyToOne(targetEntity = Student.class)
	@NonNull
	@JoinColumn(insertable = true, updatable = true, name = "student", referencedColumnName = "id", nullable = false)
	private Student student;

	@ManyToOne(targetEntity = Workgroup.class)
	@JoinColumn(insertable = true, updatable = true, name = "workgroup", referencedColumnName = "id", nullable = false)
	private Workgroup workgroup;

	public Doubt() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

}
