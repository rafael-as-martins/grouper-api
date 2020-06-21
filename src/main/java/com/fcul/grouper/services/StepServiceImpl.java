package com.fcul.grouper.services;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.model.Step;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.model.keys.StepKey;
import com.fcul.grouper.repository.StepRepository;
import com.fcul.grouper.rest.resources.project.ProjectStepsResource;
import com.fcul.grouper.rest.resources.step.StepResource;
import com.fcul.grouper.services.interfaces.ProjectService;
import com.fcul.grouper.services.interfaces.StepService;

@Service
public class StepServiceImpl implements StepService {

	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private ProjectService projectService;

	@Override
	public Step findByIdProjectAndIdStepOrder(long projectId, long stepOrder) {
		return stepRepository.findById_ProjectAndId_StepOrder(projectId, stepOrder);
	}

	@Override
	public long insertStep(StepResource stepResource) {

		Step step = mapResourceToEntity(stepResource);
		long projectId = stepResource.getProjectId();

		projectService.findById(projectId).addStep(step);

		stepRepository.save(step);

		return step.getStepOrder();

	}

	private Step mapResourceToEntity(final StepResource stepResource) {

		Step step = new Step();

		step.setName(stepResource.getName());
		step.setObjetives(stepResource.getObjetives());
		step.setStartDate(stepResource.getStartDate());
		step.setEndDate(stepResource.getEndDate());
		step.setStepOrder(stepRepository.countByIdProject(stepResource.getProjectId()) + 1);

		return step;
	}

	@Override
	public void updateEndDate(final Long projectId, final Long stepOrder, final Date endDate) {

		Step step = stepRepository.getOne(new StepKey(projectId, stepOrder));

		step.setEndDate(endDate);

		stepRepository.save(step);

	}

	@Override
	public Set<Step> findByProjectId(ProjectStepsResource projectStepsResource) {
		return stepRepository.findByProjectId(projectStepsResource.getProjectId());
	}

	@Override
	public void removeById(long projectId, long stepOrder) {
		stepRepository.deleteById(new StepKey(projectId, stepOrder));
	}

	@Override
	public Set<Task> findTasksById(Long projectId, Long stepOrder) {
		return stepRepository.findTasksById(projectId, stepOrder);
	}

	@Override
	public Set<StepTask> findStepTasksById(Long projectId, Long stepOrder) {
		return stepRepository.findStepTasksById(projectId, stepOrder);
	}

	@Override
	public void update(final StepResource stepResource) {

		long projectId = stepResource.getProjectId();
		long stepOrder = stepResource.getStepOrder();
		Step step = findByIdProjectAndIdStepOrder(projectId, stepOrder);
		
		if(step != null) {
			step.setEndDate(stepResource.getEndDate());
			step.setStartDate(stepResource.getStartDate());
			step.setObjetives(stepResource.getObjetives());
			step.setName(stepResource.getName());
			
			stepRepository.save(step);
		}

	}

}
