package com.fcul.grouper.rest.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

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

import com.fcul.grouper.exceptions.lectiveCourse.LectiveCourseNotFoundException;
import com.fcul.grouper.exceptions.project.ProjectNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.resources.project.ProjectDoubtsResource;
import com.fcul.grouper.rest.resources.project.ProjectResource;
import com.fcul.grouper.rest.resources.project.ProjectStepsResource;
import com.fcul.grouper.rest.resources.project.ProjectUpdateEndDateResource;
import com.fcul.grouper.rest.resources.project.ProjectUpdateStatusResource;
import com.fcul.grouper.rest.resources.project.ProjectWorkgroups;
import com.fcul.grouper.services.interfaces.DoubtService;
import com.fcul.grouper.services.interfaces.ProjectService;
import com.fcul.grouper.services.interfaces.StepService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ProjectController {

	private static final Logger log = Logger.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;

	@Autowired
	private StepService stepService;

	@Autowired
	private DoubtService doubtService;

	@RequestMapping(path = "project/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Project project = projectService.findById(id);

		if (project != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ProjectResource(project));
		}

		log.info("Project with id " + id + " not found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/project/endDate", method = { RequestMethod.PATCH })
	@Transactional
	public ResponseEntity<Object> updateProject(@RequestBody ProjectUpdateEndDateResource projectUpdateEndDateResource)
			throws ParseException {

		projectService.updateEndDate(projectUpdateEndDateResource.getProjectId(),
				projectUpdateEndDateResource.getEndDate());

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(path = "/project/status", method = { RequestMethod.PATCH })
	@Transactional
	public ResponseEntity<Object> updateProject(@RequestBody ProjectUpdateStatusResource projectUpdateStatusResource)
			throws ParseException {

		projectService.updateStatus(projectUpdateStatusResource.getProjectId(),
				projectUpdateStatusResource.getStatus());

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(path = "/project", method = { RequestMethod.POST })
	@Transactional
	public ResponseEntity<Object> insert(@RequestBody ProjectResource projectResource) throws ParseException {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectResource));
		} catch (LectiveCourseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Transactional
	@RequestMapping(path = "/project/steps", method = { RequestMethod.GET })
	public ResponseEntity<Object> getSteps(@RequestBody ProjectStepsResource projectStepsResource) {

		Set<Step> steps = stepService.findByProjectId(projectStepsResource);

		if (!steps.isEmpty()) {
			projectStepsResource.addStepsLinks(steps);
			return ResponseEntity.status(HttpStatus.OK).body(projectStepsResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Transactional
	@RequestMapping(path = "/project/doubts", method = { RequestMethod.GET })
	public ResponseEntity<Object> getDoubts(@RequestBody ProjectDoubtsResource projectDoubtsResource) {

		Set<Doubt> doubts = doubtService.findByProjectId(projectDoubtsResource.getProjectId());

		if (!doubts.isEmpty()) {
			projectDoubtsResource.addDoubts(doubts);
			return ResponseEntity.status(HttpStatus.OK).body(projectDoubtsResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/project/workgroups", method = { RequestMethod.GET })
	public ResponseEntity<Object> getWorkgroups(@RequestBody ProjectWorkgroups projectWorkgroups) {

		List<Workgroup> workgroups = projectService.findWorkgroupsByProjectIdWithPagination(projectWorkgroups);

		if (!workgroups.isEmpty()) {
			projectWorkgroups.addWorkgroups(workgroups);
			return ResponseEntity.status(HttpStatus.OK).body(projectWorkgroups);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/project/{projectId}", method = { RequestMethod.DELETE })
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable final long projectId) {

		try {

			projectService.delete(projectId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(path = "/project", method = { RequestMethod.PUT })
	@Transactional
	public ResponseEntity<Object> update(@RequestBody final ProjectResource projectResource) {

		try {
			projectService.update(projectResource);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (ProjectNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
