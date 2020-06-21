package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fcul.grouper.model.keys.LectiveCourseKey;

@Entity
@Table(name = "lective_course")
public class LectiveCourse implements Serializable {

	private static final long serialVersionUID = -676972627343570162L;

	@EmbeddedId
	private LectiveCourseKey id;

	@ManyToOne
	@MapsId("professor")
	@JoinColumn(name = "professor")
	private Professor professor;

	@ManyToOne
	@MapsId("course")
	@JoinColumn(name = "course")
	private Course course;

	@OneToMany(mappedBy = "lectiveCourse")
	private Set<Project> projects;

	@OneToMany(mappedBy = "lectiveCourse")
	private Set<Class> classes;

	public LectiveCourse() {
		this.id = new LectiveCourseKey();
	}

	public void addClass(Class c) {
		getClasses().add(c);
		c.setLectiveCourse(this);
	}

	public void addProject(Project project) {
		getProjects().add(project);
		project.setLectiveCourse(this);

	}

	public LectiveCourseKey getId() {
		return id;
	}

	public String getYear() {
		return id.getYear();
	}

	public void setYear(String year) {
		id.setYear(year);
	}

	public void setId(LectiveCourseKey id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Project> getProjects() {

		if (projects != null) {
			projects = new HashSet<>();
		}

		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Class> getClasses() {

		if (classes == null) {
			classes = new HashSet<Class>();
		}

		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

}
