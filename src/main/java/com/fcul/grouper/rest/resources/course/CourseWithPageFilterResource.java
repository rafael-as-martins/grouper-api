package com.fcul.grouper.rest.resources.course;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.rest.controller.CourseController;

public class CourseWithPageFilterResource extends RepresentationModel<CourseWithPageFilterResource> {

	private String name;

	private Long total;

	private int page;

	private int pageSize;

	public CourseWithPageFilterResource(String name, int page, int pageSize, Long total) {
		super();
		this.name = name;
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;
	}

	public void addCourseLinks(List<Course> courses) {

		int courseIndex = 1;
		StringBuilder relBuilder = new StringBuilder();

		for (Course course : courses) {

			String rel = relBuilder.append("Course (").append(courseIndex++).append(")").toString();
			add(linkTo(methodOn(CourseController.class).get(course.getId())).withRel(rel));
			relBuilder.setLength(0);

		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
