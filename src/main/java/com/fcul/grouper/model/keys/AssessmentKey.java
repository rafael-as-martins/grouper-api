package com.fcul.grouper.model.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssessmentKey implements Serializable {

	private static final long serialVersionUID = 3490612268524694833L;

	@Column(name = "issuing_student")
	private Long issuingStudent;

	@Column(name = "receiver_student")
	private Long receiverStudent;

	@Column(name = "workgroup")
	private Long workgroup;

	public AssessmentKey() {
	}

	public AssessmentKey(Long issuingStudent, Long receiverStudent, Long workgroup) {
		this.issuingStudent = issuingStudent;
		this.receiverStudent = receiverStudent;
		this.workgroup = workgroup;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		AssessmentKey other = (AssessmentKey) obj;

		if (issuingStudent == null) {
			if (other.getIssuingStudent() != null) {
				return false;
			}
		} else if (!issuingStudent.equals(other.getIssuingStudent())) {
			return false;
		}

		if (receiverStudent == null) {
			if (other.getReceiverStudent() != null) {
				return false;
			}
		} else if (!receiverStudent.equals(other.getReceiverStudent())) {
			return false;
		}

		if (workgroup == null) {
			if (other.getWorkgroup() != null) {
				return false;
			}
		} else if (!workgroup.equals(other.getWorkgroup())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((issuingStudent == null) ? 0 : issuingStudent.hashCode());
		result = prime * result + ((receiverStudent == null) ? 0 : receiverStudent.hashCode());
		result = prime * result + ((workgroup == null) ? 0 : workgroup.hashCode());

		return result;
	}

	public Long getIssuingStudent() {
		return issuingStudent;
	}

	public void setIssuingStudent(Long issuingStudent) {
		this.issuingStudent = issuingStudent;
	}

	public Long getReceiverStudent() {
		return receiverStudent;
	}

	public void setReceiverStudent(Long receiverStudent) {
		this.receiverStudent = receiverStudent;
	}

	public Long getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Long workgroup) {
		this.workgroup = workgroup;
	}

}
