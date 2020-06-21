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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "task")
@NamedQueries({
	@NamedQuery(name = Task.QUERY_FIND_BY_WORKGROUP_ID, query = "SELECT t FROM Task t JOIN t.group w WHERE w.id = :workgroupId")
})
public class Task implements Serializable {

	private static final long serialVersionUID = 3503493119314741950L;

	public static final String QUERY_FIND_BY_WORKGROUP_ID = "Task.findByWorkgroupId"; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "content", nullable = false)
	@NonNull
	@Lob
	private String content;

	@Column(name = "state", nullable = false)
	@NonNull
	private Boolean state;

	@ManyToOne(targetEntity = Step.class)
	@JoinColumns({
			@JoinColumn(insertable = true, updatable = true, name = "project", referencedColumnName = "project", nullable = false),
			@JoinColumn(insertable = true, updatable = true, name = "step_order", referencedColumnName = "step_order", nullable = false) 
			})
	@NonNull
	private Step step;

	@ManyToOne(targetEntity = Workgroup.class)
	@JoinColumn(insertable = true, updatable = true, name = "workgroup", referencedColumnName = "id")
	@NonNull
	private Workgroup group;

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

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Workgroup getGroup() {
		return group;
	}

	public void setGroup(Workgroup group) {
		this.group = group;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}
