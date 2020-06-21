package com.fcul.grouper.services.interfaces;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Invite;
import com.fcul.grouper.rest.resources.invite.InviteResource;

public interface InviteService {

	public Invite findById(final long id);

	public void create(InviteResource inviteResource) throws StudentNotFoundException, WorkgroupNotFoundException;

}
