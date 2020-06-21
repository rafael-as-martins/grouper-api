package com.fcul.grouper.rest.controller;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.rest.resources.professor.ProfessorCoursesProjectsResource;
import com.fcul.grouper.rest.resources.professor.ProfessorCoursesResource;
import com.fcul.grouper.rest.resources.professor.ProfessorResource;
import com.fcul.grouper.rest.resources.professor.ProfessorWithPageFilterResource;
import com.fcul.grouper.services.interfaces.ProfessorService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ProfessorController {

	private static final Logger log = Logger.getLogger(ProfessorController.class);

	@Autowired
	private ProfessorService professorService;

	@RequestMapping(path = "/professor/courses", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@RequestBody ProfessorCoursesResource professorCoursesResource) {

		String year = professorCoursesResource.getYear();
		Long professorId = professorCoursesResource.getProfessorId();

		Set<Course> courses = professorService.findCoursesByYear(year, professorId);

		if (!courses.isEmpty()) {
			professorCoursesResource.addLinks(courses);
			return ResponseEntity.status(HttpStatus.OK).body(professorCoursesResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/professor/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Professor professor = professorService.findById(id);

		log.debug("/professor/" + id + " HTTP METHOD REQUESTED");

		if (professor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ProfessorResource(professor));
		}

		log.info("Professor with id " + id + " not found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@Transactional
	@RequestMapping(path = "/professor/{id}", method = { RequestMethod.PUT })
	public ResponseEntity<Object> updateProfessor(@RequestBody final ProfessorResource professorResource,
			@PathVariable final long id) {

		professorService.updateProfessor(professorResource, id);

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@RequestMapping(path = "/professor/courses/projects", method = { RequestMethod.GET })
	public ResponseEntity<Object> insert(
			@RequestBody ProfessorCoursesProjectsResource professorCoursesProjectsResource) {

		String year = professorCoursesProjectsResource.getYear();
		Long professorId = professorCoursesProjectsResource.getProfessorId();
		Long courseId = professorCoursesProjectsResource.getCourseId();

		Set<Project> projects = professorService.findProjectsByYearAndIdAndCourse(year, professorId, courseId);

		if (!projects.isEmpty()) {
			professorCoursesProjectsResource.addProjects(projects);

			return ResponseEntity.status(HttpStatus.OK).body(professorCoursesProjectsResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PreAuthorize("hasRole('ROLE_STAFF')")
	@RequestMapping(path = "/professors", method = { RequestMethod.GET })
	public ResponseEntity<Object> getProfessorsWithSearchFilter(
			@RequestBody ProfessorWithPageFilterResource professorWithPageFilterResource) {

		try {

			int page = professorWithPageFilterResource.getPage();
			int pageSize = professorWithPageFilterResource.getPageSize();
			String search = professorWithPageFilterResource.getSearch();

			List<Professor> professors = professorService.findBySearchFilterAndInstituition(search, page, pageSize);

			if (professors.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			professorWithPageFilterResource.addProfessorLinks(professors);
			professorWithPageFilterResource.setTotal(professorService.countBySearchFilterAndInstituition(search, page, pageSize));

			return ResponseEntity.status(HttpStatus.OK).body(professorWithPageFilterResource);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
