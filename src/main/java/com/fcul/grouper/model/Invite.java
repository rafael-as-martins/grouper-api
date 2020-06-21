package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "invite")
public class Invite implements Serializable {

	private static final long serialVersionUID = -1593321220585617938L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_date", nullable = false)
	@NonNull
	private Date creationDate;

	@ManyToOne(targetEntity = Student.class)
	@NonNull
	@JoinColumn(insertable = true, updatable = true, name = "issuing_student", referencedColumnName = "id", nullable = false)
	private Student issuingStudent;

	@ManyToOne(targetEntity = Workgroup.class)
	@NonNull
	@JoinColumn(insertable = true, updatable = true, name = "workgroup", referencedColumnName = "id", nullable = false)
	private Workgroup workgroup;

	@ManyToOne(targetEntity = Student.class)
	@NonNull
	@JoinColumn(insertable = true, updatable = true, name = "receiver_student", referencedColumnName = "id", nullable = false)
	private Student receiverStudent;

	public Invite() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getIssuingStudent() {
		return issuingStudent;
	}

	public void setIssuingStudent(Student issuingStudent) {
		this.issuingStudent = issuingStudent;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public Student getReceiverStudent() {
		return receiverStudent;
	}

	public void setReceiverStudent(Student receiverStudent) {
		this.receiverStudent = receiverStudent;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
