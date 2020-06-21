package com.fcul.grouper.rest.resources.project;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.rest.controller.LectiveCourseController;
import com.fcul.grouper.rest.controller.WorkgroupController;
import com.fcul.grouper.utils.DateConverterUtil;

public class ProjectResource extends RepresentationModel<ProjectResource> implements Serializable {

	private static final long serialVersionUID = -3970807765532160169L;

	private Long id;

	private String name;

	private Date startDate;

	private Date endDate;

	private Integer minElems;

	private Integer maxElems;

	private Boolean classRestriction;

	private Long professorId;

	private Long courseId;

	private String year;

	private Boolean status;

	private String description;

	public ProjectResource(Long id, String name, String startDate, String endDate, Integer minElems, Integer maxElems,
			Boolean classRestriction, Long professorId, Long courseId, String year, Boolean status, String description)
			throws ParseException {
		this.id = id;
		this.name = name;
		this.startDate = DateConverterUtil.extractDate(startDate);
		this.endDate = DateConverterUtil.extractDate(endDate);
		this.minElems = minElems;
		this.maxElems = maxElems;
		this.classRestriction = classRestriction;
		this.professorId = professorId;
		this.courseId = courseId;
		this.year = year;
		this.status = status;
		this.description = description;

	}

	public ProjectResource(final Project project) {

		id = project.getId();
		name = project.getName();
		startDate = project.getStartDate();
		endDate = project.getEndDate();
		minElems = project.getMinElems();
		maxElems = project.getMaxElems();
		classRestriction = project.getClassRestriction();
		status = project.getStatus();
		description = project.getDescription();

		if (project.getWorkgroups() != null && !project.getWorkgroups().isEmpty()) {
			add(linkTo(methodOn(WorkgroupController.class).getProjectWorkgroups(project.getId()))
					.withRel("Workgroups"));
		}

		LectiveCourse lectiveCourse = project.getLectiveCourse();

		if (lectiveCourse != null) {

			year = lectiveCourse.getYear();
			courseId = lectiveCourse.getCourse().getId();
			professorId = lectiveCourse.getProfessor().getId();

			add(linkTo(methodOn(LectiveCourseController.class).get(year, courseId, professorId))
					.withRel("Lective Course"));
		}

	}

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

	public Integer getMaxElems() {
		return maxElems;
	}

	public void setMaxElems(Integer maxElems) {
		this.maxElems = maxElems;
	}

	public Boolean getClassRestriction() {
		return classRestriction;
	}

	public void setClassRestriction(Boolean classRestriction) {
		this.classRestriction = classRestriction;
	}

	public Integer getMinElems() {
		return minElems;
	}

	public void setMinElems(Integer minElems) {
		this.minElems = minElems;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
