package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "course")
@NamedQueries({
		@NamedQuery(name = Course.QUERY_FIND_COURSE_PROJECTS, query = "SELECT p FROM Course c JOIN c.lectiveCourses lc JOIN lc.projects p WHERE c.id = :"
				+ Course.PARAM_COURSE_ID + " AND lc.id.year = :" + Course.PARAM_YEAR),
		@NamedQuery(name = Course.QUERY_FIND_BY_NAME_AND_INSTITUITION, query = "SELECT c FROM Course c JOIN c.instituition i WHERE i.id = :"
				+ Course.PARAM_INSTITUITION + " AND c.name like :" + Course.PARAM_NAME),
		@NamedQuery(name = Course.QUERY_COUNT_BY_NAME_AND_INSTITUITION, query = "SELECT count(c) FROM Course c JOIN c.instituition i WHERE i.id = :"
				+ Course.PARAM_INSTITUITION + " AND c.name like :" + Course.PARAM_NAME)})
public class Course implements Serializable {

	private static final long serialVersionUID = 8420771769839471586L;

	public static final String QUERY_FIND_COURSE_PROJECTS = "Course.findProjectsByCourseIdAndYear";
	public static final String QUERY_FIND_BY_NAME_AND_INSTITUITION = "Course.findByInstituitionAndName";
	public static final String QUERY_COUNT_BY_NAME_AND_INSTITUITION = "Course.countByInstituitionAndName";

	public static final String PARAM_YEAR = "year";
	public static final String PARAM_COURSE_ID = "courseId";
	public static final String PARAM_INSTITUITION = "instituitionId";
	public static final String PARAM_NAME = "name";

	@Id
	private Long id;

	@Column(name = "name")
	@Size(max = 255)
	@NonNull
	private String name;

	@ManyToOne(targetEntity = Instituition.class)
	@JoinColumn(insertable = true, updatable = true, name = "instituition", referencedColumnName = "id")
	@NonNull
	private Instituition instituition;

	@OneToMany(mappedBy = "course")
	private Set<LectiveCourse> lectiveCourses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addLectiveCourse(LectiveCourse lectiveCourse) {
		getLectiveCourses().add(lectiveCourse);
		lectiveCourse.setCourse(this);
	}

	public Set<LectiveCourse> getLectiveCourses() {

		if (lectiveCourses == null) {
			lectiveCourses = new HashSet<>();
		}

		return lectiveCourses;
	}

	public void setLectiveCourses(Set<LectiveCourse> lectiveCourses) {
		this.lectiveCourses = lectiveCourses;
	}

	public Instituition getInstituition() {
		return instituition;
	}

	public void setInstituition(Instituition instituition) {
		this.instituition = instituition;
	}

}
