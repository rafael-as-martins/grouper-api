package com.fcul.grouper.rest.resources.workgroup;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.WorkgroupController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class WorkgroupsResource extends RepresentationModel<WorkgroupResource> {

	public WorkgroupsResource(List<Workgroup> workgroups) {

		int index = 1;
		StringBuilder sb = new StringBuilder();

		for (Workgroup workgroup : workgroups) {

			String rel = sb.append("Workgroup (").append(index++).append(")").toString();
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId())).withRel(rel));
			sb.setLength(0);

		}
	}

}
