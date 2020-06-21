package com.fcul.grouper.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.model.Workgroup;

@Repository
public interface WorkgroupRepository extends CrudRepository<Workgroup, Long> {

	public Workgroup findById(long id);

	public List<Workgroup> findByStudents_Id(final long studentId);

	public List<Workgroup> findByProject_Id(final long projectId);
	
	public Set<Doubt> findDoubtsByWorkgroupId(@Param("id") Long id);
}
