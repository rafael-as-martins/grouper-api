package com.fcul.grouper.rest.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.rest.resources.step.StepResource;
import com.fcul.grouper.rest.resources.step.StepTaksResource;
import com.fcul.grouper.rest.resources.step.task.StepTasksResource;
import com.fcul.grouper.rest.resources.student.StepUpdateEndDateResource;
import com.fcul.grouper.services.interfaces.StepService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class StepController {

	private static final Logger log = Logger.getLogger(StepController.class);

	@Autowired
	private StepService stepService;

	@GetMapping("/step/{order}/project/{projectId}")
	@Transactional
	public ResponseEntity<Object> get(@PathVariable final long order, @PathVariable final long projectId) {

		Step step = stepService.findByIdProjectAndIdStepOrder(projectId, order);

		log.debug("/step/" + order + "/project/" + projectId + " HTTP METHOD REQUESTED");

		if (step != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new StepResource(step));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@RequestMapping(path = "/step", method = { RequestMethod.POST })
	@Transactional
	public ResponseEntity<Object> post(@Valid @RequestBody final StepResource stepResource) {

		try {
			long stepOrder = stepService.insertStep(stepResource);
			return ResponseEntity.status(HttpStatus.CREATED).body(stepOrder);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@RequestMapping(path = "/step", method = { RequestMethod.PATCH })
	@Transactional
	public ResponseEntity<Object> createStep(@RequestBody StepUpdateEndDateResource stepUpdateEndDateResource)
			throws ParseException {

		Long projectId = stepUpdateEndDateResource.getProjectId();
		Long stepOrder = stepUpdateEndDateResource.getStepOrder();
		Date endDate = stepUpdateEndDateResource.getEndDate();

		stepService.updateEndDate(projectId, stepOrder, endDate);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(path = "/step", method = { RequestMethod.DELETE })
	public ResponseEntity<Object> remove(@RequestBody StepResource stepResource) {

		try {
			stepService.removeById(stepResource.getProjectId(), stepResource.getStepOrder());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "/step/tasks", method = { RequestMethod.GET })
	public ResponseEntity<Object> remove(@RequestBody StepTaksResource stepTaksResource) {

		try {

			long projectId = stepTaksResource.getProjectId();
			long stepOrder = stepTaksResource.getStepOrder();

			Set<Task> tasks = stepService.findTasksById(projectId, stepOrder);

			if (tasks.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			stepTaksResource.addTasks(tasks);

			return ResponseEntity.status(HttpStatus.OK).body(stepTaksResource);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "/step/step-tasks", method = { RequestMethod.GET })
	public ResponseEntity<Object> remove(@RequestBody StepTasksResource stepTasksResource) {

		try {

			long projectId = stepTasksResource.getProjectId();
			long stepOrder = stepTasksResource.getStepOrder();

			Set<StepTask> stepTasks = stepService.findStepTasksById(projectId, stepOrder);

			if (stepTasks.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			stepTasksResource.addStepTasksLinks(stepTasks);

			return ResponseEntity.status(HttpStatus.OK).body(stepTasksResource);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "/step", method = { RequestMethod.PUT })
	public ResponseEntity<Object> update(@RequestBody StepResource stepResource) {

		try {

			stepService.update(stepResource);

			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
