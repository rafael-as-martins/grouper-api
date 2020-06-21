package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.keys.ClassKey;

@Repository
public interface ClassRepository extends CrudRepository<com.fcul.grouper.model.Class, ClassKey> {

	public Class findByIdCourseAndIdNameAndIdYear(final long courseId, final String name, final String year);

}
