package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "workgroup")
@NamedQueries({
		@NamedQuery(name = Workgroup.QUERY_FIND_DOUBTS_BY_WORKGROUP_ID, query = "SELECT db FROM Workgroup w JOIN w.doubts db WHERE w.id = :"
				+ Workgroup.PARAM_WORKGROUP_ID) })
public class Workgroup implements Serializable {

	private static final long serialVersionUID = -5686672921244251126L;

	public static final String QUERY_FIND_DOUBTS_BY_WORKGROUP_ID = "Workgroup.findDoubtsByWorkgroupId";

	public static final String PARAM_WORKGROUP_ID = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(insertable = true, updatable = true, name = "project", referencedColumnName = "id")
	@NonNull
	private Project project;

	@ManyToOne(targetEntity = Class.class)
	@JoinColumns({ @JoinColumn(updatable = true, insertable = true, name = "course", referencedColumnName = "course"),
			@JoinColumn(updatable = true, insertable = true, name = "name", referencedColumnName = "name"),
			@JoinColumn(updatable = true, insertable = true, name = "year", referencedColumnName = "year") })
	@NonNull
	private Class workgroupClass;

	@ManyToMany(mappedBy = "workgroups")
	private Set<Student> students;

	@OneToMany(mappedBy = "workgroup")
	private Set<Message> messages;

	@OneToMany(mappedBy = "workgroup")
	private Set<Doubt> doubts;

	@OneToMany(mappedBy = "group")
	private Set<Task> tasks;

	@OneToMany(mappedBy = "workgroup")
	private Set<Feedback> feedbacks;

	@OneToMany(mappedBy = "workgroup", orphanRemoval = true)
	private Set<File> files;
	
	public void addStudent(Student student) {
		getStudents().add(student);
	}

	public void addFile(File file) {
		getFiles().add(file);
		file.setWorkgroup(this);
	}

	public void addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setWorkgroup(this);
	}

	public void addTask(Task task) {
		getTasks().add(task);
		task.setGroup(this);
	}

	public void addDoubt(Doubt doubt) {
		getDoubts().add(doubt);
		doubt.setWorkgroup(this);
	}

	public void addMessage(Message message) {
		getMessages().add(message);
		message.setWorkgroup(this);
	}

	public Workgroup() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Class getWorkgroupClass() {
		return workgroupClass;
	}

	public void setWorkgroupClass(Class workgroupClass) {
		this.workgroupClass = workgroupClass;
	}

	public Set<Student> getStudents() {

		if (students == null) {
			students = new HashSet<>();
		}

		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Message> getMessages() {

		if (messages == null) {
			messages = new HashSet<>();
		}

		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Doubt> getDoubts() {

		if (doubts == null) {
			doubts = new HashSet<>();
		}

		return doubts;
	}

	public void setDoubts(Set<Doubt> doubts) {
		this.doubts = doubts;
	}

	public Set<Feedback> getFeedbacks() {

		if (feedbacks == null) {
			this.feedbacks = new HashSet<>();
		}

		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<File> getFiles() {

		if (files == null) {
			files = new HashSet<File>();
		}

		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	public Set<Task> getTasks() {

		if (tasks != null) {
			tasks = new HashSet<>();
		}

		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
