package com.fcul.grouper.rest.resources.doubt;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.rest.controller.DoubtController;;

public class MultipleDoubtsResource extends RepresentationModel<MultipleDoubtsResource> {

	public void addDoubts(final Set<Doubt> doubts) {

		int doubtIndex = 1;
		for (Doubt doubt : doubts) {
			add(linkTo(methodOn(DoubtController.class).get(doubt.getId())).withRel("Doubt " + doubtIndex++));
		}

	}

}
