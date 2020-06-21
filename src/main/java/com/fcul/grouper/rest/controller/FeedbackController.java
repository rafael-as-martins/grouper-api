package com.fcul.grouper.rest.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.exceptions.course.CourseNotFoundException;
import com.fcul.grouper.exceptions.professor.ProfessorNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Feedback;
import com.fcul.grouper.rest.resources.FeedbackResource;
import com.fcul.grouper.services.FeedbackSeviceImpl;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class FeedbackController {

	private static final Logger log = Logger.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackSeviceImpl feedbackSevice;

	@RequestMapping(path = "/feedback/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> getFeedback(@PathVariable final long id) {

		Feedback feedback = feedbackSevice.findById(id);

		if (feedback != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new FeedbackResource(feedback));
		}

		log.info("Feedback with id " + id + " not found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/feedback/{id}", method = { RequestMethod.PUT })
	public ResponseEntity<HttpStatus> updateFeedback(@RequestBody final FeedbackResource feedbackResource,
			final long id) {

		feedbackSevice.updateFeedback(feedbackResource, id);

		log.info("Feedback with id " + id + " updated");
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@Transactional
	@RequestMapping(path = "/feedback", method = { RequestMethod.POST })
	public ResponseEntity<Object> createFeedback(@Valid @RequestBody final FeedbackResource feedbackResource) {

		try {

			feedbackSevice.createFeedback(feedbackResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (WorkgroupNotFoundException | ProfessorNotFoundException | CourseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
