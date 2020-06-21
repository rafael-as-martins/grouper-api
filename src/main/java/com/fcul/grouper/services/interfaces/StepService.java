package com.fcul.grouper.services.interfaces;

import java.util.Date;
import java.util.Set;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.rest.resources.project.ProjectStepsResource;
import com.fcul.grouper.rest.resources.step.StepResource;

public interface StepService {

	public Step findByIdProjectAndIdStepOrder(final long projectId, final long stepOrder);

	public long insertStep(final StepResource stepResource);

	public void updateEndDate(final Long projectId, final Long stepOrder, final Date endDate);

	public Set<Step> findByProjectId(final ProjectStepsResource projectStepsResource);

	public void removeById(final long projectId, final long stepOrder);

	public Set<Task> findTasksById(Long projectId, Long stepOrder);

	public Set<StepTask> findStepTasksById(Long projectId, Long stepOrder);
	
	public void update(StepResource stepResource);

}
