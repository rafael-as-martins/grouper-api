package com.fcul.grouper.rest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Assessment;
import com.fcul.grouper.rest.resources.assessment.AssessmentResource;
import com.fcul.grouper.services.interfaces.AssessmentService;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class AssessmentController {

	@Autowired
	private AssessmentService assessmentService;

	@RequestMapping(path = "/assessment", method = { RequestMethod.GET })
	public ResponseEntity<Object> get(@RequestBody AssessmentResource assessmentResource) {

		Long issuerId = assessmentResource.getIssuerId();
		Long receiverId = assessmentResource.getReceiverId();
		Long workgroupId = assessmentResource.getWorkgroupId();

		Assessment assessment = assessmentService.findByIdIssuingStudentAndIdReceiverStudentAndIdWorkgroup(issuerId,
				receiverId, workgroupId);

		if (assessment != null) {
			assessmentResource.updateResource(assessment);
			return ResponseEntity.status(HttpStatus.OK).build();
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@Transactional
	@RequestMapping(path = "/assessment", method = { RequestMethod.POST })
	public ResponseEntity<Object> post(@RequestBody AssessmentResource assessmentResource) {

		try {

			assessmentService.insert(assessmentResource);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (StudentNotFoundException | WorkgroupNotFoundException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}


	}

}
