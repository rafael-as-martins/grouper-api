package com.fcul.grouper.rest.resources.student;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Student;
import com.fcul.grouper.rest.controller.StudentsController;

public class StudentWithPageFilterResource extends RepresentationModel<StudentWithPageFilterResource> {

	private String search;

	private Long total;

	private int page;

	private int pageSize;

	public StudentWithPageFilterResource(String search, int page, int pageSize, long total) {
		super();
		this.search = search;
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;
	}

	public void addStudentsLinks(List<Student> students) {

		int studentIndex = 1;
		StringBuilder relBuilder = new StringBuilder();

		for (Student student : students) {

			String rel = relBuilder.append("Student (").append(studentIndex++).append(")").toString();
			add(linkTo(methodOn(StudentsController.class).get(student.getId())).withRel(rel));
			relBuilder.setLength(0);

		}

	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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
