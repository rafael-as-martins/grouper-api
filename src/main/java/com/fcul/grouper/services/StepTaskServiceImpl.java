package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.repository.StepTaskRepository;
import com.fcul.grouper.rest.resources.step.task.StepTaskResource;
import com.fcul.grouper.services.interfaces.StepService;
import com.fcul.grouper.services.interfaces.StepTaskService;

@Service
public class StepTaskServiceImpl implements StepTaskService {

	@Autowired
	private StepTaskRepository stepTaskRepository;

	@Autowired
	private StepService stepService;

	@Override
	public StepTask findById(long id) {
		return stepTaskRepository.findById(id);
	}

	@Override
	public void create(StepTaskResource stepTaskResource) throws WorkgroupNotFoundException {

		StepTask stepTask = new StepTask();
		stepTask.setContent(stepTaskResource.getContent());

		stepService.findByIdProjectAndIdStepOrder(stepTaskResource.getProjectId(), stepTaskResource.getStepOrder())
				.addStepTask(stepTask);

		stepTaskRepository.save(stepTask);

	}

}
