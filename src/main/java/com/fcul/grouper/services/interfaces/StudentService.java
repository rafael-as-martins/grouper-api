package com.fcul.grouper.services.interfaces;

import java.util.List;
import java.util.Set;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.rest.resources.course.CourseStudentsResource;
import com.fcul.grouper.rest.resources.student.StudentResource;
import com.fcul.grouper.services.upload.UploadController;

public interface StudentService extends UploadController {

	public void updateStudent(final StudentResource studentResource, final long id);

	public void insertStudent(final StudentResource studentResource);

	public Student findById(Long id) throws StudentNotFoundException;

	public Set<Course> findCoursesByYear(String year, Long id);

	public Set<Project> findProjectsByYearAndIdAndCourse(String year, Long id, Long course);

	public List<Student> findStudentsByCourseAndYearAndClass(final CourseStudentsResource courseStudentsResource);

	public Class findClassesByCourseId(final long courseId, final long studentId);

	public void save(Student student);

	public List<Student> findBYSearchFilterAndInstituition(String search, Integer page, Integer pageSize);
	
	public Long countBySearchFilterAndInstituition(String search, Integer page, Integer pageSize);

}
