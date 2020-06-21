package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.keys.ClassKey;

@Entity
@Table(name = "class")
public class Class implements Serializable {

	private static final long serialVersionUID = -1057922343962295118L;

	@EmbeddedId
	private ClassKey id;

	@ManyToOne
	@NonNull
	@JoinColumns({
			@JoinColumn(updatable = false, insertable = false, name = "professor", referencedColumnName = "professor"),
			@JoinColumn(updatable = false, insertable = false, name = "course", referencedColumnName = "course"),
			@JoinColumn(updatable = false, insertable = false, name = "year", referencedColumnName = "year") })
	private LectiveCourse lectiveCourse;

	@Column(name = "professor")
	private Long professorId;

	@OneToMany(mappedBy = "workgroupClass")
	private Set<Workgroup> workgroups;

	@ManyToMany(mappedBy = "classes")
	private Set<Student> students;

	public void addWorkgroup(Workgroup workgroup) {
		getWorkgroups().add(workgroup);
		workgroup.setWorkgroupClass(this);
	}

	public Class() {
		this.id = new ClassKey();
	}

	public Class(ClassKey id, LectiveCourse lectiveCourse) {
		this.id = id;
		this.lectiveCourse = lectiveCourse;
	}

	public long getCourseId() {
		return id.getCourse();
	}

	public String getYear() {
		return id.getYear();
	}

	public ClassKey getId() {
		return id;
	}

	public void setId(ClassKey id) {
		this.id = id;
	}

	public String getName() {
		return id.getName();
	}

	public void setName(String name) {
		this.id.setName(name);
	}

	public LectiveCourse getLectiveCourse() {
		return lectiveCourse;
	}

	public void setLectiveCourse(LectiveCourse lectiveCourse) {
		this.id.setCourse(lectiveCourse.getCourse().getId());
		this.id.setYear(lectiveCourse.getYear());
		this.professorId = lectiveCourse.getProfessor().getId();
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Workgroup> getWorkgroups() {

		if (workgroups == null) {
			workgroups = new HashSet<Workgroup>();
		}

		return workgroups;
	}

	public void setWorkgroups(Set<Workgroup> workgroups) {
		this.workgroups = workgroups;
	}

}
