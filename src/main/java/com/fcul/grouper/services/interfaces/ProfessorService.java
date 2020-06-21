package com.fcul.grouper.services.interfaces;

import java.util.List;
import java.util.Set;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.rest.resources.professor.ProfessorResource;
import com.fcul.grouper.services.upload.UploadController;

public interface ProfessorService extends UploadController {

	public Set<Course> findCoursesByYear(String year, Long professorId);

	public void updateProfessor(final ProfessorResource professorResource, final long id);

	public Professor findById(final long id);

	public Set<Project> findProjectsByYearAndIdAndCourse(String year, Long professorId, Long course);

	public List<Professor> findBySearchFilterAndInstituition(String search, Integer page, Integer pageSize);
	
	public Long countBySearchFilterAndInstituition(String search, Integer page, Integer pageSize);

}
