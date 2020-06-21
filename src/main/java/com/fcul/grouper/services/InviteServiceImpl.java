package com.fcul.grouper.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Invite;
import com.fcul.grouper.repository.InviteRepository;
import com.fcul.grouper.rest.resources.invite.InviteResource;
import com.fcul.grouper.services.interfaces.InviteService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class InviteServiceImpl implements InviteService {

	@Autowired
	private InviteRepository inviteRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private WorkgroupService workgroupService;

	@Override
	public Invite findById(long id) {
		return inviteRepository.findById(id);
	}

	@Override
	public void create(InviteResource inviteResource) throws StudentNotFoundException, WorkgroupNotFoundException {

		Invite invite = new Invite();

		invite.setCreationDate(new Date());

		invite.setIssuingStudent(studentService.findById(inviteResource.getIssuingStudent()));
		invite.setReceiverStudent(studentService.findById(inviteResource.getReceiverStudent()));
		invite.setWorkgroup(workgroupService.findById(inviteResource.getWorkgroupId()));

		inviteRepository.save(invite);
	}

}
