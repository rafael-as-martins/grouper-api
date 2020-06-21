package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Invite;
import com.fcul.grouper.rest.resources.invite.InviteResource;
import com.fcul.grouper.services.interfaces.InviteService;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class InviteController {

	private static final Logger log = Logger.getLogger(InviteController.class);

	@Autowired
	private InviteService inviteService;

	@RequestMapping(path = "/invite/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Invite invite = inviteService.findById(id);

		log.debug("/invite/" + id + " HTTP METHOD REQUESTED");

		if (invite != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new InviteResource(invite));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/invite", method = { RequestMethod.POST })
	public ResponseEntity<Object> get(@RequestBody final InviteResource inviteResource) {

		try {

			inviteService.create(inviteResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (StudentNotFoundException | WorkgroupNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
