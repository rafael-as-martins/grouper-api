package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.model.keys.LectiveCourseKey;

@Repository
public interface LectiveCourseRepository extends CrudRepository<LectiveCourse, LectiveCourseKey> {

	public LectiveCourse findByIdYearAndIdProfessorAndIdCourse(final String year, final long professor, final long course);
}
