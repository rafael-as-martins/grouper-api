package com.fcul.grouper.rest.resources.workgroup;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.File;
import com.fcul.grouper.rest.controller.FileController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class WorkgroupFilesResource extends RepresentationModel<WorkgroupFilesResource> {

	private Long workgroupId;

	public WorkgroupFilesResource() {
		super();
	}

	public WorkgroupFilesResource(Long workgroupId) {
		super();
		this.workgroupId = workgroupId;
	}

	public void addFiles(final Set<File> files) {

		int filesIndex = 1;
		for (File file : files) {
			add(linkTo(methodOn(FileController.class).getFileMeta(file.getId())).withRel("File " + filesIndex++));
		}

	}

	public Long getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

}
