package com.fcul.grouper.rest.resources.course;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Student;
import com.fcul.grouper.rest.controller.StudentsController;

public class CourseStudentsResource extends RepresentationModel<CourseStudentsResource> {

	private String year;

	private Long courseId;

	private String search;

	private String orderField;

	private Integer page;

	private Integer pageSize;

	private Boolean withGroups;

	public void addStudents(List<Student> students) {

		int studentIndex = 1;
		for (Student student : students) {
			add(linkTo(methodOn(StudentsController.class).get(student.getId())).withRel("Student " + studentIndex++));
		}

	}

	public CourseStudentsResource(String year, Long courseId, String search, String orderField, Integer page,
			Integer pageSize, Boolean withGroups) {
		super();
		this.year = year;
		this.courseId = courseId;
		this.search = search;
		this.orderField = orderField;
		this.page = page;
		this.pageSize = pageSize;
		this.withGroups = withGroups;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getWithGroups() {
		return withGroups;
	}

	public void setWithGroups(Boolean withGroups) {
		this.withGroups = withGroups;
	}

	
}
