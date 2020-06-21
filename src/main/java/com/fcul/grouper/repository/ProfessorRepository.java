package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.model.Project;


@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long>{
	
	public Professor findById(long id);
	
	public Set<Course> findCoursesByYear(@Param("year") String year, @Param("professorId") Long professorId);
	
	public Set<Project> findProjectsByYearAndIdAndCourse(@Param(Professor.PARAM_YEAR) String year, @Param(Professor.PARAM_PROFESSOR) Long professorId, @Param(Professor.PARAM_COURSE) Long courseId);
	
	public Professor findByEmail(final String email);

}
