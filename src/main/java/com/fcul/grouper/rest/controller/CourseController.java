package com.fcul.grouper.rest.controller;

import java.util.List;
import java.util.Set;

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
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.rest.resources.course.CourseProjectsResource;
import com.fcul.grouper.rest.resources.course.CourseResource;
import com.fcul.grouper.rest.resources.course.CourseStudentsResource;
import com.fcul.grouper.rest.resources.course.CourseWithPageFilterResource;
import com.fcul.grouper.services.interfaces.CourseService;
import com.fcul.grouper.services.interfaces.StudentService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CourseController {

	private static final Logger log = Logger.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@RequestMapping(path = "/course/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		Course course = courseService.findById(id);

		if (course != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new CourseResource(course));
		}

		log.info("Course with id " + id + " not found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/course/students", method = { RequestMethod.GET })
	public ResponseEntity<Object> getCourseStudents(@RequestBody CourseStudentsResource courseStudentsResource) {

		List<Student> students = studentService.findStudentsByCourseAndYearAndClass(courseStudentsResource);

		if (students.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		courseStudentsResource.addStudents(students);

		return ResponseEntity.status(HttpStatus.OK).body(courseStudentsResource);

	}

	@RequestMapping(path = "/course/projects", method = { RequestMethod.GET })
	public ResponseEntity<Object> getCourseProjects(@RequestBody CourseProjectsResource courseProjectsResource) {

		try {

			long courseId = courseProjectsResource.getCourseId();
			String year = courseProjectsResource.getYear();

			Set<Project> projects = courseService.findProjectsByCourseIdAndYear(courseId, year);

			if (projects.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			courseProjectsResource.addProjects(projects);

			return ResponseEntity.status(HttpStatus.OK).body(courseProjectsResource);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}

	}

	@PreAuthorize("hasRole('ROLE_STAFF')")
	@RequestMapping(path = "/courses", method = { RequestMethod.GET })
	public ResponseEntity<Object> getCoursesWithSearchFilter(
			@RequestBody CourseWithPageFilterResource courseWithPageFilterResource) {

		try {

			int page = courseWithPageFilterResource.getPage();
			int pageSize = courseWithPageFilterResource.getPageSize();
			String name = courseWithPageFilterResource.getName();

			List<Course> courses = courseService.findByInstituitionAndName(name, page, pageSize);

			if (courses.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			courseWithPageFilterResource.addCourseLinks(courses);
			courseWithPageFilterResource.setTotal(courseService.countByInstituitionAndName(name, page, pageSize));

			return ResponseEntity.status(HttpStatus.OK).body(courseWithPageFilterResource);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
