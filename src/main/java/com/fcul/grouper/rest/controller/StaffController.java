package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.Staff;
import com.fcul.grouper.rest.resources.staff.StaffResource;
import com.fcul.grouper.services.interfaces.StaffService;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class StaffController {

	private static final Logger log = Logger.getLogger(CourseController.class);

	@Autowired
	private StaffService staffService;

	@GetMapping("/staff/{id}")
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Staff staff = staffService.findById(id);

		if (staff != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new StaffResource(staff));
		}

		log.info("Staff with id " + id + " not found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
