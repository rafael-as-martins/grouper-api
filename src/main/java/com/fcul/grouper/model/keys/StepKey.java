package com.fcul.grouper.model.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StepKey implements Serializable {

	private static final long serialVersionUID = 9191396062415443534L;

	@Column(name = "project")
	private Long project;

	@Column(name = "step_order")
	private Long stepOrder;

	public StepKey(Long project, Long stepOrder) {
		this.project = project;
		this.stepOrder = stepOrder;
	}

	public StepKey() {
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

		StepKey other = (StepKey) obj;

		if (project == null) {
			if (other.getProject() != null) {
				return false;
			}
		} else if (!project.equals(other.getProject())) {
			return false;
		}

		if (stepOrder == null) {
			if (other.getStepOrder() != null) {
				return false;
			}
		} else if (!stepOrder.equals(other.getStepOrder())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((stepOrder == null) ? 0 : stepOrder.hashCode());

		return result;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public Long getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Long stepOrder) {
		this.stepOrder = stepOrder;
	}

}
