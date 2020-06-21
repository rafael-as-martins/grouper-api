package com.fcul.grouper.services.interfaces;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Assessment;
import com.fcul.grouper.rest.resources.assessment.AssessmentResource;

public interface AssessmentService {
	
	public Assessment findByIdIssuingStudentAndIdReceiverStudentAndIdWorkgroup(final Long issuingStudentId, final Long receiverStudentId, final Long workgroupId);
	
	public void insert(final AssessmentResource assessmentResource) throws StudentNotFoundException, WorkgroupNotFoundException;
}
