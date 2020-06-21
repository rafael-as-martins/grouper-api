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

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.rest.resources.doubt.DoubtResource;
import com.fcul.grouper.services.interfaces.DoubtService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class DoubtController {

	private static final Logger log = Logger.getLogger(DoubtController.class);

	@Autowired
	private DoubtService doubtService;

	@RequestMapping(path = "/doubt/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		log.debug("/doubt/" + id + " HTTP GET REQUEST METHOD");

		Doubt doubt = doubtService.findById(id);

		if (doubt != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new DoubtResource(doubt));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Transactional
	@RequestMapping(path = "/doubt", method = { RequestMethod.POST })
	public ResponseEntity<Object> post(@RequestBody final DoubtResource doubtResource) {

		log.debug("/doubt HTTP POST REQUESTED METHOD");

		try {
			doubtService.insert(doubtResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (StudentNotFoundException | WorkgroupNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "/doubt", method = { RequestMethod.PATCH })
	public ResponseEntity<Object> put(@RequestBody final DoubtResource doubtResource) {

		doubtService.addAnswer(doubtResource);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
