package com.fcul.grouper.services.interfaces;

import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.services.upload.UploadController;

public interface LectiveCourseService extends UploadController{

	public LectiveCourse findByIdYearAndIdProfessorAndIdCourse(final String year, final long professor, final long course);
	
}
