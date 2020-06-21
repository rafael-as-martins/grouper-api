package com.fcul.grouper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "step_task")
public class StepTask implements Serializable {

	private static final long serialVersionUID = 8481519716215283104L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(targetEntity = Step.class)
	@JoinColumns({ @JoinColumn(updatable = true, insertable = true, name = "project", referencedColumnName = "project"),
			@JoinColumn(updatable = true, insertable = true, name = "step_order", referencedColumnName = "step_order"), })
	private Step step;

	@Column(name = "content", nullable = false)
	@NonNull
	@Lob
	private String content;

	public StepTask() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
