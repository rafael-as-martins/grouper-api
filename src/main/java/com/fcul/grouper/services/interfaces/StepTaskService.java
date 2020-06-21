package com.fcul.grouper.services.interfaces;

import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.StepTask;
import com.fcul.grouper.rest.resources.step.task.StepTaskResource;

public interface StepTaskService {

	public StepTask findById(final long id);

	public void create(StepTaskResource stepTaskResource) throws WorkgroupNotFoundException;

}
