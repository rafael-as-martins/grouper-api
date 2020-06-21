package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	public Task findById(long id);
	
	public Task getOne(long id);
	
	public Set<Task> findByWorkgroupId(@Param("workgroupId") final Long workgroupId);
}
