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

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.rest.resources.student.StudentClassResource;
import com.fcul.grouper.rest.resources.student.StudentCoursesProjectsResource;
import com.fcul.grouper.rest.resources.student.StudentCoursesResource;
import com.fcul.grouper.rest.resources.student.StudentResource;
import com.fcul.grouper.rest.resources.student.StudentWithPageFilterResource;
import com.fcul.grouper.rest.resources.student.StudentWorkgroupResource;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class StudentsController {

	private static final Logger log = Logger.getLogger(StudentsController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private WorkgroupService workgroupService;

	@RequestMapping(path = "/student/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		try {

			Student student = studentService.findById(id);

			return ResponseEntity.status(HttpStatus.OK).body(new StudentResource(student));

		} catch (StudentNotFoundException e) {

			log.info("Student with id " + id + " not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@RequestMapping(path = "/student/courses", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@RequestBody StudentCoursesResource studentCoursesResource) {

		Set<Course> courses = studentService.findCoursesByYear(studentCoursesResource.getYear(),
				studentCoursesResource.getStudentId());

		if (!courses.isEmpty()) {
			studentCoursesResource.addCourses(courses);
			return ResponseEntity.status(HttpStatus.OK).body(studentCoursesResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@Transactional
	@RequestMapping(path = "/student/{id}", method = { RequestMethod.PUT })
	public ResponseEntity<Object> insertStudent(@RequestBody StudentResource student, @PathVariable final long id) {

		try {

			studentService.updateStudent(student, id);
			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Transactional
	@RequestMapping(path = "/student", method = { RequestMethod.POST })
	public ResponseEntity<Object> insert(@RequestBody StudentResource studentResource) {

		try {
			studentService.insertStudent(studentResource);

			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@RequestMapping(path = "/student/courses/projects", method = { RequestMethod.GET })
	public ResponseEntity<Object> insert(@RequestBody StudentCoursesProjectsResource studentCoursesProjectsResource) {

		String year = studentCoursesProjectsResource.getYear();
		Long studentId = studentCoursesProjectsResource.getStudentId();
		Long courseId = studentCoursesProjectsResource.getCourseId();

		Set<Project> projects = studentService.findProjectsByYearAndIdAndCourse(year, studentId, courseId);

		if (!projects.isEmpty()) {
			studentCoursesProjectsResource.addProjects(projects);
			return ResponseEntity.status(HttpStatus.OK).body(studentCoursesProjectsResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/student/class", method = { RequestMethod.GET })
	public ResponseEntity<Object> getStudentClassByCourse(@RequestBody StudentClassResource studentClassResource) {

		if (studentClassResource.getCourseId() != null && studentClassResource.getStudentId() != null) {

			Class studentClass = studentService.findClassesByCourseId(studentClassResource.getCourseId(),
					studentClassResource.getStudentId());

			if (studentClass != null) {
				studentClassResource.addClass(studentClass);
				return ResponseEntity.status(HttpStatus.OK).body(studentClassResource);
			}
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@RequestMapping(path = "/student/workgroup", method = { RequestMethod.DELETE })
	@Transactional
	public ResponseEntity<Object> removeAssociation(@RequestBody StudentWorkgroupResource studentWorkgroupResource) {

		try {

			long workgroupId = studentWorkgroupResource.getWorkgroupId();
			long studentId = studentWorkgroupResource.getStudentId();

			int result = workgroupService.removeStudentFromWorkgroupByWorkgroupIdAndStudentId(workgroupId, studentId);

			if (result == 0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PreAuthorize("hasRole('ROLE_STAFF')")
	@RequestMapping(path = "/students", method = { RequestMethod.GET })
	public ResponseEntity<Object> getStudentsWithSearchFilter(
			@RequestBody StudentWithPageFilterResource studentWithPageFilterResource) {

		try {

			int page = studentWithPageFilterResource.getPage();
			int pageSize = studentWithPageFilterResource.getPageSize();
			String search = studentWithPageFilterResource.getSearch();

			List<Student> students = studentService.findBYSearchFilterAndInstituition(search, page, pageSize);

			if (students.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			studentWithPageFilterResource.addStudentsLinks(students);
			studentWithPageFilterResource
					.setTotal(studentService.countBySearchFilterAndInstituition(search, page, pageSize));

			return ResponseEntity.status(HttpStatus.OK).body(studentWithPageFilterResource);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
