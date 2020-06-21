package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.StepTask;

@Repository
public interface StepTaskRepository extends CrudRepository<StepTask, Long> {

	public StepTask findById(final long id);
	
}
