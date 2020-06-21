package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.rest.resources.InstituitionResource;
import com.fcul.grouper.services.interfaces.InstituitionService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class InstituitionController {

	private static final Logger log = Logger.getLogger(InstituitionController.class);

	@Autowired
	private InstituitionService instituitionService;

	@GetMapping("/instituition/{id}")
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Instituition instituition = instituitionService.findById(id);

		if (instituition != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new InstituitionResource(instituition));
		}

		log.info("Instituition with id " + id + " not found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
