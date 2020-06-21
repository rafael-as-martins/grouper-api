package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long>{

	public Feedback findById(long id);
	
	public Set<Feedback> findByWorkgroupId(@Param("workgroupId") final Long workgroupId);
	
	
}
