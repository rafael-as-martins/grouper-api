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
import com.fcul.grouper.model.Message;
import com.fcul.grouper.rest.resources.MessageResource;
import com.fcul.grouper.services.interfaces.MessageService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MessageController {

	private static final Logger log = Logger.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@RequestMapping(path = "/message/{id}", method = { RequestMethod.GET })
	@Transactional
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Message message = messageService.findById(id);

		log.debug("/message/" + id + " HTTP METHOD REQUESTED");

		if (message != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResource(message));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/message", method = { RequestMethod.POST })
	@Transactional
	public ResponseEntity<Object> post(@RequestBody final MessageResource messageResource) {

		log.debug("/message/ POST HTTP METHOD REQUESTED");

		try {
			messageService.insertMessage(messageResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (StudentNotFoundException | WorkgroupNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
