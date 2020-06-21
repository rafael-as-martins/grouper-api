package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	public Project findById(long id);

	public Project getOne(final Long id);

}
