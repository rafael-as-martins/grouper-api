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

import com.fcul.grouper.exceptions.task.TaskNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.rest.resources.TaskResource;
import com.fcul.grouper.services.interfaces.TaskService;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class TaskController {

	private static final Logger log = Logger.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	@RequestMapping(path = "/task/{id}", method = { RequestMethod.GET })
	public ResponseEntity<TaskResource> getTask(@PathVariable final long id) {

		Task task = taskService.findById(id);

		if (task != null) {
			return new ResponseEntity<TaskResource>(new TaskResource(task), HttpStatus.OK);
		}

		log.info("Task with id " + id + " not found");
		return new ResponseEntity<TaskResource>(HttpStatus.NOT_FOUND);

	}

	@Transactional
	@RequestMapping(path = "/task/{id}", method = { RequestMethod.PUT })
	public ResponseEntity<HttpStatus> updateTask(@RequestBody TaskResource taskResource, @PathVariable final long id) {

		taskService.updateTask(taskResource, id);

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@Transactional
	@RequestMapping(path = "task", method = { RequestMethod.POST })
	public ResponseEntity<Object> createTask(@RequestBody final TaskResource taskResource) {

		try {
			taskService.createTask(taskResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (WorkgroupNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "task", method = { RequestMethod.PATCH })
	public ResponseEntity<Object> updateTask(@RequestBody final TaskResource taskResource) {

		try {
			taskService.updateTask(taskResource);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (TaskNotFoundException e) {

			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
