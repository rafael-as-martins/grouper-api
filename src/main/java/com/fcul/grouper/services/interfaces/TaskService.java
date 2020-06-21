package com.fcul.grouper.services.interfaces;

import java.util.Set;

import com.fcul.grouper.exceptions.task.TaskNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Task;
import com.fcul.grouper.rest.resources.TaskResource;

public interface TaskService {

	public void updateTask(final TaskResource taskResource, final long id);

	public Task findById(final long id);

	public void createTask(final TaskResource taskResource) throws WorkgroupNotFoundException;

	public void updateTask(final TaskResource taskResource) throws TaskNotFoundException;

	public Set<Task> findByWorkgroupId(final Long workgroupId);
}
