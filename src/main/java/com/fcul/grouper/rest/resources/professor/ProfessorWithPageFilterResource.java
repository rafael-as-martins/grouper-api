package com.fcul.grouper.rest.resources.professor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Professor;
import com.fcul.grouper.rest.controller.ProfessorController;

public class ProfessorWithPageFilterResource extends RepresentationModel<ProfessorWithPageFilterResource> {

	private String search;

	private Long total;

	private int page;

	private int pageSize;

	public ProfessorWithPageFilterResource(String search, int page, int pageSize, Long total) {
		super();
		this.search = search;
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;
	}

	public void addProfessorLinks(List<Professor> professors) {

		int professorIndex = 1;
		StringBuilder relBuilder = new StringBuilder();

		for (Professor professor : professors) {

			String rel = relBuilder.append("Professor (").append(professorIndex++).append(")").toString();
			add(linkTo(methodOn(ProfessorController.class).get(professor.getId())).withRel(rel));
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
