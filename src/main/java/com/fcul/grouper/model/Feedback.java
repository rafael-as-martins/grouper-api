package com.fcul.grouper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

import com.fcul.grouper.model.types.FeedbackType;

@Entity
@Table(name = "feedback")
@NamedQueries({
		@NamedQuery(name = Feedback.QUERY_FIND_BY_WORKGORUP_ID, query = "SELECT f FROM Feedback f JOIN f.workgroup w WHERE w.id = :workgroupId") })
public class Feedback implements Serializable {

	private static final long serialVersionUID = 3003254496225746561L;

	public static final String QUERY_FIND_BY_WORKGORUP_ID = "Feedback.findByWorkgroupId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NonNull
	private FeedbackType type;

	@Column(name = "grade", nullable = true)
	@Range(min = 0, max = 20)
	private Double grade;

	@Column(name = "content", nullable = false)
	@Lob
	private String content;

	@ManyToOne(targetEntity = Workgroup.class)
	@JoinColumn(insertable = true, updatable = true, name = "workgroup", referencedColumnName = "id")
	@NonNull
	private Workgroup workgroup;

	@ManyToOne(targetEntity = Professor.class)
	@JoinColumn(insertable = true, updatable = true, name = "professor", referencedColumnName = "id")
	@NonNull
	private Professor professor;

	@ManyToOne(targetEntity = Course.class)
	@NonNull
	@JoinColumn(insertable = true, updatable = true, name = "course", referencedColumnName = "id")
	private Course course;

	@OneToOne(targetEntity = Step.class)
	@JoinColumns({
			@JoinColumn(insertable = true, updatable = true, name = "project", referencedColumnName = "project", nullable = false),
			@JoinColumn(insertable = true, updatable = true, name = "step_order", referencedColumnName = "step_order", nullable = false) })
	private Step step;

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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public FeedbackType getType() {
		return type;
	}

	public void setType(FeedbackType type) {
		this.type = type;
	}

}
