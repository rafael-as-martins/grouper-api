package com.fcul.grouper.services.interfaces;

import java.util.Set;

import org.springframework.data.repository.query.Param;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.rest.resources.doubt.DoubtResource;

public interface DoubtService {

	public Doubt findById(final long id);

	public void insert(final DoubtResource doubtResource) throws StudentNotFoundException, WorkgroupNotFoundException;

	public void addAnswer(final DoubtResource doubtResource);

	public Set<Doubt> findByWorkgroupId(@Param("workgroupId") final long workgroupId);

	public Set<Doubt> findByProjectId(@Param("projectId") final long projectId);

}
