package com.fcul.grouper.services;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Message;
import com.fcul.grouper.repository.MessageRepository;
import com.fcul.grouper.rest.resources.MessageResource;
import com.fcul.grouper.services.interfaces.MessageService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private WorkgroupService workgroupService;

	@Override
	public Message findById(long id) {
		return messageRepository.findById(id);
	}

	@Override
	public void insertMessage(final MessageResource messageResource) throws StudentNotFoundException, WorkgroupNotFoundException{

		Message message = mapResourceIntoEntity(messageResource);

		studentService.findById(messageResource.getStudentId()).addMessage(message);
		workgroupService.findById(messageResource.getWorkgroupId()).addMessage(message);

		if (messageResource.getAnswerTo() != null) {

			Message answerTo = findById(messageResource.getAnswerTo());
			answerTo.addAnswer(message);

		}

		messageRepository.save(message);
	}

	public Message mapResourceIntoEntity(final MessageResource messageResource) {

		Message message = new Message();

		message.setContent(messageResource.getContent());
		message.setCreationDate(new Date());

		return message;

	}

	@Override
	public Set<Long> findIdsByWorkgroup(Long workgroupId) {
		return messageRepository.findIdsByWorkgroup(workgroupId);
	}

}
