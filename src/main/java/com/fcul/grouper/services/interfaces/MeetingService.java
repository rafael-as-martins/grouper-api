package com.fcul.grouper.services.interfaces;

import java.util.Set;

import com.fcul.grouper.exceptions.meeting.MeetingNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Meeting;
import com.fcul.grouper.rest.resources.meeting.MeetingResource;

public interface MeetingService {

	public Meeting findById(final Long id) throws MeetingNotFoundException;

	public void insert(final MeetingResource meetingResource) throws WorkgroupNotFoundException;
	
	public Set<Meeting> findByWorkgroupId(final Long workgroupId);
	
	public void update(MeetingResource meetingResource);
	
}
