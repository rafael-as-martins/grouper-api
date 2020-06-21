package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.rest.resources.LectiveCourseResource;
import com.fcul.grouper.services.interfaces.LectiveCourseService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class LectiveCourseController {

	private static final Logger log = Logger.getLogger(LectiveCourseController.class);

	@Autowired
	private LectiveCourseService lectiveCourseService;

	@GetMapping("/lectiveCourse/{year}/course/{courseId}/professor/{professorId}")
	public ResponseEntity<Object> get(@PathVariable final String year, @PathVariable final long courseId,
			@PathVariable final long professorId) {

		log.debug("/lectiveCourse/" + year + "/course/" + courseId + "/professor/" + professorId);

		LectiveCourse lectiveCourse = lectiveCourseService.findByIdYearAndIdProfessorAndIdCourse(year, courseId,
				professorId);

		if (lectiveCourse != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new LectiveCourseResource(lectiveCourse));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/lectiveCourse/course/{courseId}")
	public ResponseEntity<String> getLectiveCoursesByCourse(@PathVariable final long courseId) {
		return new ResponseEntity<String>("Not Implemented", HttpStatus.OK);
	}

}
