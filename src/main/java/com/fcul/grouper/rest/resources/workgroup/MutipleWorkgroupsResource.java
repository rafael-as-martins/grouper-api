package com.fcul.grouper.rest.resources.workgroup;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.controller.WorkgroupController;

public class MutipleWorkgroupsResource extends RepresentationModel<MutipleWorkgroupsResource> {

	public void addWorkgroups(final List<Workgroup> workgroups) {

		int doubtIndex = 1;
		for (Workgroup workgroup : workgroups) {
			add(linkTo(methodOn(WorkgroupController.class).get(workgroup.getId()))
					.withRel("Workgroup " + doubtIndex++));
		}

	}

}