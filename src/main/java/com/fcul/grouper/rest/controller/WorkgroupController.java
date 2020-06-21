package com.fcul.grouper.rest.controller;

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

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.model.Feedback;
import com.fcul.grouper.model.File;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupAssociationResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupDoubtsResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupFeedbacksResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupFilesResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupMessagesResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupTasksResource;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupsResource;
import com.fcul.grouper.services.interfaces.DoubtService;
import com.fcul.grouper.services.interfaces.FeedbackService;
import com.fcul.grouper.services.interfaces.FileService;
import com.fcul.grouper.services.interfaces.TaskService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class WorkgroupController {

	private static final Logger log = Logger.getLogger(WorkgroupController.class);

	@Autowired
	private WorkgroupService workgroupService;

	@Autowired
	private FileService fileService;

	@Autowired
	private DoubtService doubtService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(path = "/student/{studentId}/workgroups", method = { RequestMethod.GET })
	public ResponseEntity<Object> getStudentWorkgroups(@PathVariable final long studentId) {

		try {
			List<Workgroup> workgroups = workgroupService.findByStudentId(studentId);

			if (workgroups.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			return ResponseEntity.status(HttpStatus.OK).body(new WorkgroupsResource(workgroups));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@RequestMapping(path = "/project/{projectId}/workgroups", method = { RequestMethod.GET })
	public ResponseEntity<Object> getProjectWorkgroups(@PathVariable final long projectId) {

		try {

			List<Workgroup> workgroups = workgroupService.findByProjectId(projectId);

			if (workgroups.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			return ResponseEntity.status(HttpStatus.OK).body(new WorkgroupsResource(workgroups));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@RequestMapping(path = "/workgroup/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(new WorkgroupResource(workgroupService.findById(id)));
		} catch (WorkgroupNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@Transactional
	@RequestMapping(path = "/workgroup/associate", method = { RequestMethod.POST })
	public ResponseEntity<Object> createWorkgroup(
			@RequestBody WorkgroupAssociationResource workgroupAssociationResource) {

		try {

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(workgroupService.addStudentAssociation(workgroupAssociationResource));
		} catch (WorkgroupNotFoundException | StudentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "/workgroup/messages", method = { RequestMethod.GET })
	public ResponseEntity<WorkgroupMessagesResource> get(
			@RequestBody WorkgroupMessagesResource workgroupMessagesResource) {

		log.debug("/workgroup/messages HTTP GET REQUEST METHOD");

		Set<Long> messages = workgroupService.findMessagesByid(workgroupMessagesResource.getWorkgroupId());

		if (!messages.isEmpty()) {
			workgroupMessagesResource.addMessages(messages);
			return new ResponseEntity<WorkgroupMessagesResource>(workgroupMessagesResource, HttpStatus.OK);
		}

		return new ResponseEntity<WorkgroupMessagesResource>(workgroupMessagesResource, HttpStatus.NO_CONTENT);

	}

	@Transactional
	@RequestMapping(path = "/workgroup/files", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@RequestBody WorkgroupFilesResource workgroupFilesResource) {

		Set<File> files = fileService.findByWorkgroupId(workgroupFilesResource.getWorkgroupId());

		if (!files.isEmpty()) {

			workgroupFilesResource.addFiles(files);
			return ResponseEntity.status(HttpStatus.OK).body(workgroupFilesResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@Transactional
	@RequestMapping(path = "/workgroup/doubts", method = { RequestMethod.GET })
	public ResponseEntity<Object> getDoubts(@RequestBody WorkgroupDoubtsResource workgroupDoubtsResource) {

		Set<Doubt> doubts = doubtService.findByWorkgroupId(workgroupDoubtsResource.getWorkgroupId());

		if (!doubts.isEmpty()) {
			workgroupDoubtsResource.addDoubts(doubts);
			return ResponseEntity.status(HttpStatus.OK).body(workgroupDoubtsResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@Transactional
	@RequestMapping(path = "/workgroup/tasks", method = { RequestMethod.GET })
	public ResponseEntity<Object> getTasks(@RequestBody WorkgroupTasksResource workgroupTasksResource) {

		Set<Task> doubts = taskService.findByWorkgroupId(workgroupTasksResource.getWorkgroupId());

		if (!doubts.isEmpty()) {
			workgroupTasksResource.addTasks(doubts);
			return ResponseEntity.status(HttpStatus.OK).body(workgroupTasksResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@Transactional
	@RequestMapping(path = "/workgroup/feedbacks", method = { RequestMethod.GET })
	public ResponseEntity<Object> getFeedbacks(@RequestBody WorkgroupFeedbacksResource workgroupFeedbacksResource) {

		Set<Feedback> feedbacks = feedbackService.findByWorkgroupId(workgroupFeedbacksResource.getWorkgroupId());

		if (!feedbacks.isEmpty()) {
			workgroupFeedbacksResource.addFeedbacks(feedbacks);
			return ResponseEntity.status(HttpStatus.OK).body(workgroupFeedbacksResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
