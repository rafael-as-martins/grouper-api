package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	public Student findById(long id);

	public Set<Course> findCoursesByYear(@Param("year") String year, @Param("id") Long id);

	public Set<Project> findProjectsByYearAndIdAndCourse(@Param("year") String year, @Param("id") Long id,
			@Param("course") Long course);

	public Student findByEmail(final String email);

	public Class findClassesByCourseId(@Param("courseId") final long courseId,
			@Param("studentId") final long studentId);

}
