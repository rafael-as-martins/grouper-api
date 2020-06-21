package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Assessment;
import com.fcul.grouper.model.keys.AssessmentKey;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment, AssessmentKey> {
	
	public Assessment findByIdIssuingStudentAndIdReceiverStudentAndIdWorkgroup(final long issuingStudentId, final long receiverStudentId, final long workgroupId);
	
	
}
