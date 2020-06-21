package com.fcul.grouper.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.meeting.MeetingNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Meeting;
import com.fcul.grouper.repository.MeetingRepository;
import com.fcul.grouper.rest.resources.meeting.MeetingResource;
import com.fcul.grouper.services.interfaces.MeetingService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;

	@Autowired
	private WorkgroupService workgroupService;

	@Override
	public Meeting findById(Long id) throws MeetingNotFoundException {
		Optional<Meeting> meeting = meetingRepository.findById(id);

		if (!meeting.isPresent()) {
			throw new MeetingNotFoundException();
		}

		return meeting.get();
	}

	@Override
	public void insert(MeetingResource meetingResource) throws WorkgroupNotFoundException {

		Meeting meeting = new Meeting();

		meeting.setDuration(meetingResource.getDuration());
		meeting.setDateTime(meetingResource.getDateTime());
		meeting.setWorkgroup(workgroupService.findById(meetingResource.getWorkgroupId()));

		meetingRepository.save(meeting);

	}

	@Override
	public Set<Meeting> findByWorkgroupId(Long workgroupId) {
		return meetingRepository.findByWorkgroupId(workgroupId);
	}

	@Override
	public void update(MeetingResource meetingResource) {

		Meeting meeting = meetingRepository.getOne(meetingResource.getId());
		
		meeting.setDateTime(meetingResource.getDateTime());
		meeting.setDuration(meetingResource.getDuration());
		
		
		meetingRepository.save(meeting);
	}

}
