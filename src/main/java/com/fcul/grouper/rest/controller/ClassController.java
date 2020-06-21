package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.keys.ClassKey;
import com.fcul.grouper.rest.resources.sclass.ClassResource;
import com.fcul.grouper.services.ClassServiceImpl;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class ClassController {

	private static final Logger log = Logger.getLogger(ClassController.class);

	@Autowired
	private ClassServiceImpl classServiceImpl;

	@GetMapping(path = "/class/{year}/course/{courseId}/{name}")
	public ResponseEntity<Object> get(@PathVariable final String year, @PathVariable final long courseId,
			@PathVariable final String name) {

		log.info("/class/{year}/course/{courseId}/{name} HTTP METHOD REQUESTED");

		com.fcul.grouper.model.Class subject = classServiceImpl.findById(new ClassKey(courseId, name, year));

		if (subject != null) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new ClassResource(subject));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
