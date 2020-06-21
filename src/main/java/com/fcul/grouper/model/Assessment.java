package com.fcul.grouper.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.keys.AssessmentKey;

@Entity
@Table(name = "assessment")
public class Assessment {

	@EmbeddedId
	private AssessmentKey id;

	@ManyToOne
	@MapsId("issuingStudent")
	@JoinColumn(name = "issuing_student", referencedColumnName = "id")
	private Student issuingStudent;

	@ManyToOne
	@MapsId("receiverStudent")
	@JoinColumn(name = "receiver_student", referencedColumnName = "id")
	private Student receiverStudent;

	@ManyToOne
	@MapsId("workgroup")
	@JoinColumn(name = "workgroup", referencedColumnName = "id")
	private Workgroup workgroup;

	@Column(name = "grade", nullable = false)
	@NonNull
	private Double grade;

	public Assessment() {
	}

	public AssessmentKey getId() {
		return id;
	}

	public void setId(AssessmentKey id) {
		this.id = id;
	}

	public Student getIssuingStudent() {
		return issuingStudent;
	}

	public void setIssuingStudent(Student issuingStudent) {
		this.issuingStudent = issuingStudent;
	}

	public Student getReceiverStudent() {
		return receiverStudent;
	}

	public void setReceiverStudent(Student receiverStudent) {
		this.receiverStudent = receiverStudent;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
	
	

}
