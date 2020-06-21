package com.fcul.grouper.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.task.TaskNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.repository.TaskRepository;
import com.fcul.grouper.rest.resources.TaskResource;
import com.fcul.grouper.services.interfaces.StepService;
import com.fcul.grouper.services.interfaces.TaskService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private WorkgroupService workgroupService;

	@Autowired
	private StepService stepService;

	@Override
	public void updateTask(TaskResource taskResource, final long id) {

		Task task = taskRepository.findById(id);

		if (task != null) {

			task.setContent(taskResource.getContent());
			task.setState(taskResource.getState());

		}

	}

	@Override
	public Task findById(final long id) {
		return taskRepository.findById(id);
	}

	@Override
	public void createTask(TaskResource taskResource) throws WorkgroupNotFoundException {

		Task task = mapFromTaskResourceToTask(taskResource);

		workgroupService.findById(taskResource.getWorkgroupId()).addTask(task);

		stepService.findByIdProjectAndIdStepOrder(taskResource.getProjectId(), taskResource.getStepOrder())
				.addTask(task);

		taskRepository.save(task);

	}

	private Task mapFromTaskResourceToTask(final TaskResource taskResource) {

		Task task = new Task();

		task.setContent(taskResource.getContent());
		task.setState(taskResource.getState());

		return task;

	}

	@Override
	public void updateTask(TaskResource taskResource) throws TaskNotFoundException {

		Task task = taskRepository.getOne(taskResource.getId());

		if (task == null) {
			throw new TaskNotFoundException();
		}

		task.setContent(taskResource.getContent());
		task.setState(taskResource.getState());

		taskRepository.save(task);

	}

	@Override
	public Set<Task> findByWorkgroupId(Long workgroupId) {
		return taskRepository.findByWorkgroupId(workgroupId);
	}

}
