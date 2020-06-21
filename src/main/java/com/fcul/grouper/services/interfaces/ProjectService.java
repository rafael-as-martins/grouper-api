package com.fcul.grouper.services.interfaces;

import java.util.Date;
import java.util.List;

import com.fcul.grouper.exceptions.lectiveCourse.LectiveCourseNotFoundException;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.rest.resources.project.ProjectResource;
import com.fcul.grouper.rest.resources.project.ProjectWorkgroups;

public interface ProjectService {

	public Project findById(final long id);

	public long createProject(final ProjectResource projectResource) throws LectiveCourseNotFoundException;

	public void updateEndDate(final Long projectId, final Date endDate);

	public void updateStatus(final Long projectId, final Boolean status);

	public List<Workgroup> findWorkgroupsByProjectIdWithPagination(ProjectWorkgroups projectWorkgroups);

	public void delete(final Long projectId);

	public void update(final ProjectResource projectResource);

}
