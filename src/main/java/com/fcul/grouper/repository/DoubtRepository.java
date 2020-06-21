package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Doubt;

@Repository
public interface DoubtRepository extends CrudRepository<Doubt, Long> {

	public Doubt findById(final long id);

	public Doubt getOne(final Long id);

	public Set<Doubt> findByWorkgroupId(@Param("workgroupId") final long workgroupId);

	public Set<Doubt> findByProjectId(@Param("projectId") final long projectId);

}
