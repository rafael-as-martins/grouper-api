package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "message")
@NamedQueries({
		@NamedQuery(name = "Message.findIdsByWorkgroup", query = "SELECT m.id FROM Message m JOIN m.workgroup w WHERE w.id = :workgroupId") })
public class Message implements Serializable {

	private static final long serialVersionUID = -6328747415747763036L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "content", nullable = false)
	@NonNull
	@Lob
	private String content;

	@Column(name = "creation_date", nullable = false)
	@NonNull
	private Date creationDate;

	@ManyToOne(targetEntity = Workgroup.class)
	@JoinColumn(insertable = true, updatable = true, name = "workgroup", referencedColumnName = "id")
	private Workgroup workgroup;

	@ManyToOne(targetEntity = Student.class)
	@JoinColumn(insertable = true, updatable = true, name = "student", referencedColumnName = "id")
	private Student student;

	@ManyToOne(targetEntity = Message.class)
	@JoinColumn(insertable = true, updatable = true, name = "answer_to", referencedColumnName = "id")
	private Message answerTo;

	// One message can be answered by many messages
	@JoinTable(name = "message_answers", joinColumns = @JoinColumn(name = "message"), inverseJoinColumns = @JoinColumn(name = "answer"))
	@OneToMany(targetEntity = Message.class)
	private Set<Message> answers;

	public void addAnswer(final Message message) {
		getAnswers().add(message);
		message.setAnswerTo(this);
	}

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

	public Set<Message> getAnswers() {

		if (answers == null) {
			answers = new HashSet<>();
		}

		return answers;
	}

	public void setAnswers(Set<Message> answers) {
		this.answers = answers;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Message getAnswerTo() {
		return answerTo;
	}

	public void setAnswerTo(Message answerTo) {
		this.answerTo = answerTo;
	}

}
