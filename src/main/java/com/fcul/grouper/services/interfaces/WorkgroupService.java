package com.fcul.grouper.services.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;

import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupAssociationResource;

public interface WorkgroupService {

	public List<Workgroup> findByStudentId(final long studentId);

	public List<Workgroup> findByProjectId(final long projectId);

	public Workgroup findById(final Long id) throws WorkgroupNotFoundException;

	public long createWorkgroup(Long projectId, Long studentId, Long courseId, String name, String year);

	public Set<Doubt> findDoubtsByWorkgroupId(@Param("id") Long id);

	public Set<Long> findMessagesByid(final Long id);

	public void removeStudentFromWorkgroupByProjectId(final long projectId);

	public int removeStudentFromWorkgroupByWorkgroupIdAndStudentId(final long workgroupId, final long studentId);

	public long addStudentAssociation(final WorkgroupAssociationResource workgroupAssociationResource)
			throws WorkgroupNotFoundException;
}
