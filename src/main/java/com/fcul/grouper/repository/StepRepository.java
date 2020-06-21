package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.model.keys.StepKey;

@Repository
public interface StepRepository extends CrudRepository<Step, StepKey> {

	public Step findById_ProjectAndId_StepOrder(final long projectId, final long stepOrder);

	public Step getOne(final StepKey id);

	public Set<Step> findByProjectId(final long projectId);

	public long countByIdProject(final long projectId);

	public Set<Task> findTasksById(@Param(Step.PARAM_ID_PROJECT) Long projectId,
			@Param(Step.PARAM_ID_STEP_ORDER) Long stepOrder);
	
	public Set<StepTask> findStepTasksById(@Param(Step.PARAM_ID_PROJECT) Long projectId,
			@Param(Step.PARAM_ID_STEP_ORDER) Long stepOrder);

}
