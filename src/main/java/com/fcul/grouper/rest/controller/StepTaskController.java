package com.fcul.grouper.rest.controller;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.rest.resources.step.task.StepTaskResource;
import com.fcul.grouper.services.interfaces.StepTaskService;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class StepTaskController {

	private static final Logger log = Logger.getLogger(StepTaskController.class);

	@Autowired
	private StepTaskService stepTaskService;

	@RequestMapping(path = "/stepTask/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		StepTask stepTask = stepTaskService.findById(id);

		log.debug("/stepTask/" + id + " HTTP METHOD REQUESTED");

		if (stepTask != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new StepTaskResource(stepTask));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@RequestMapping(path = "/stepTask", method = { RequestMethod.POST })
	@Transactional
	public ResponseEntity<Object> post(@RequestBody final StepTaskResource stepTaskResource) {

		try {

			stepTaskService.create(stepTaskResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
