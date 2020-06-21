package com.fcul.grouper.services.interfaces;

import java.util.List;
import java.util.Set;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.services.upload.UploadController;

public interface CourseService extends UploadController {

	public Course findById(final long id);

	public Set<Project> findProjectsByCourseIdAndYear(final long courseId, final String year);
	
	public List<Course> findByInstituitionAndName(String name, Integer page, Integer pageSize);
	
	public Long countByInstituitionAndName(String name, Integer page, Integer pageSize);

}
