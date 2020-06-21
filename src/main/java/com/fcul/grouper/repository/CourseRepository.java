package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	public Course findById(long id);
	
	public Set<Project> findProjectsByCourseIdAndYear(@Param("courseId") final long courseId, @Param("year") final String year);

}
