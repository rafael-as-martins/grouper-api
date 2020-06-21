package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {

	public Meeting findById(final long id);

	public Set<Meeting> findByWorkgroupId(@Param("workgroupId") final Long workgroupId);
	
	public Meeting getOne(final long id);

}
