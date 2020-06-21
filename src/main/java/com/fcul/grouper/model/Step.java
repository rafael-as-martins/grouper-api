package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.keys.StepKey;

@Entity
@Table(name = "step")
@NamedQueries({
		@NamedQuery(name = Step.QUERY_FIND_TASKS_BY_ID, query = "SELECT t FROM Step s JOIN s.tasks t WHERE s.id.project = :"
				+ Step.PARAM_ID_PROJECT + " AND s.id.stepOrder = :" + Step.PARAM_ID_STEP_ORDER),
		@NamedQuery(name = Step.QUERY_FIND_STEP_TASKS_BY_ID, query = "SELECT st FROM Step s JOIN s.stepTasks st WHERE st.step.id.project = :"
				+ Step.PARAM_ID_PROJECT + " AND st.step.id.stepOrder = :" + Step.PARAM_ID_STEP_ORDER) })
public class Step implements Serializable {

	private static final long serialVersionUID = -4773956792007390114L;

	public static final String QUERY_FIND_TASKS_BY_ID = "Step.findTasksById";
	public static final String QUERY_FIND_STEP_TASKS_BY_ID = "Step.findStepTasksById";

	public static final String PARAM_ID_PROJECT = "projectId";
	public static final String PARAM_ID_STEP_ORDER = "stepOrder";

	@EmbeddedId
	private StepKey id;

	@ManyToOne(targetEntity = Project.class)
	@MapsId("project")
	@JoinColumn(name = "project")
	private Project project;

	@Column(name = "name", nullable = false)
	@NonNull
	@Size(max = 255)
	private String name;

	@Column(name = "objetives", nullable = false)
	@Lob
	@NonNull
	private String objetives;

	@Column(name = "star_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@OneToOne(targetEntity = Feedback.class, orphanRemoval = true, mappedBy = "step")
	@JoinColumn(insertable = true, updatable = true, name = "feedback", referencedColumnName = "id")
	private Feedback feedback;

	@OneToMany(mappedBy = "step", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Task> tasks;

	@OneToMany(mappedBy = "step", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<StepTask> stepTasks;

	public void addTask(final Task task) {
		getTasks().add(task);
		task.setStep(this);
	}

	public void addStepTask(final StepTask stepTask) {
		getStepTasks().add(stepTask);
		stepTask.setStep(this);
	}

	public void setStepOrder(final long stepOrder) {

		if (id == null) {
			id = new StepKey();
		}

		id.setStepOrder(stepOrder);
	}

	public Step() {

		this.id = new StepKey();
	}

	public Long getStepOrder() {
		return id.getStepOrder();
	}

	public StepKey getId() {
		return id;
	}

	public void setId(StepKey id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjetives() {
		return objetives;
	}

	public void setObjetives(String objetives) {
		this.objetives = objetives;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Set<Task> getTasks() {

		if (tasks == null) {
			tasks = new HashSet<Task>();
		}

		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<StepTask> getStepTasks() {

		if (stepTasks == null) {
			stepTasks = new HashSet<>();
		}

		return stepTasks;
	}

	public void setStepTasks(Set<StepTask> stepTasks) {
		this.stepTasks = stepTasks;
	}

}
