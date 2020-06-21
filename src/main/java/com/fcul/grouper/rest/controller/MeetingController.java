package com.fcul.grouper.rest.controller;

import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.exceptions.meeting.MeetingNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Meeting;
import com.fcul.grouper.rest.resources.meeting.MeetingResource;
import com.fcul.grouper.rest.resources.meeting.MeetingsResource;
import com.fcul.grouper.services.interfaces.MeetingService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MeetingController {

	private static final Logger log = Logger.getLogger(MeetingController.class);

	@Autowired
	private MeetingService meetingService;

	@RequestMapping(path = "/meeting/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@PathVariable final long id) {

		log.debug("/meeting/" + id + " HTTP METHOD REQUESTED");

		try {

			Meeting meeting = meetingService.findById(id);
			return new ResponseEntity<Object>(new MeetingResource(meeting), HttpStatus.OK);

		} catch (MeetingNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@RequestMapping(path = "/meeting", method = { RequestMethod.POST })
	public ResponseEntity<Object> post(@RequestBody final MeetingResource meetingResource) {

		log.info("/meeting POST METHOD REQUESTED");

		try {
			meetingService.insert(meetingResource);

			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (WorkgroupNotFoundException e) {

			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());

		}

	}

	@RequestMapping(path = "/meetings", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@RequestBody MeetingsResource meetingsResource) {

		Set<Meeting> meetings = meetingService.findByWorkgroupId(meetingsResource.getWorkgroupId());

		if (!meetings.isEmpty()) {

			meetingsResource.addMeetings(meetings);
			return ResponseEntity.status(HttpStatus.OK).body(meetingsResource);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/meeting", method = { RequestMethod.PATCH })
	public ResponseEntity<Object> patch(@RequestBody MeetingResource meetingResource) {
		try {

			meetingService.update(meetingResource);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
