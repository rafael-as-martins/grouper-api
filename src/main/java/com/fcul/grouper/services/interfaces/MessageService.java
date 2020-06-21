package com.fcul.grouper.services.interfaces;

import java.util.Set;

import org.springframework.data.repository.query.Param;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Message;
import com.fcul.grouper.rest.resources.MessageResource;

public interface MessageService {

	public Message findById(final long id);

	public void insertMessage(final MessageResource messageResource) throws StudentNotFoundException, WorkgroupNotFoundException;
	
	public Set<Long> findIdsByWorkgroup(@Param("workgroupId") Long workgroupId); 

}
