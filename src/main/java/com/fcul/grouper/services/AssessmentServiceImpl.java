package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Assessment;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.model.keys.AssessmentKey;
import com.fcul.grouper.repository.AssessmentRepository;
import com.fcul.grouper.rest.resources.assessment.AssessmentResource;
import com.fcul.grouper.services.interfaces.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	private AssessmentRepository assessmentRepository;

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	private WorkgroupServiceImpl workgroupServiceImpl;

	@Override
	public Assessment findByIdIssuingStudentAndIdReceiverStudentAndIdWorkgroup(final Long issuingStudentId,
			final Long receiverStudentId, final Long workgroupId) {
		return assessmentRepository.findByIdIssuingStudentAndIdReceiverStudentAndIdWorkgroup(issuingStudentId,
				receiverStudentId, workgroupId);
	}

	@Override
	public void insert(final AssessmentResource assessmentResource) throws StudentNotFoundException, WorkgroupNotFoundException {

		Student issuingStudent = studentServiceImpl.findById(assessmentResource.getIssuerId());
		Student receiverStudent = studentServiceImpl.findById(assessmentResource.getReceiverId());
		Workgroup workgroup = workgroupServiceImpl.findById(assessmentResource.getWorkgroupId());

		
		AssessmentKey id = new AssessmentKey();
		id.setIssuingStudent(issuingStudent.getId());
		id.setReceiverStudent(receiverStudent.getId());
		id.setWorkgroup(workgroup.getId());
		
		Assessment assessment = new Assessment();

		assessment.setId(id);
		assessment.setIssuingStudent(issuingStudent);
		assessment.setReceiverStudent(receiverStudent);
		assessment.setWorkgroup(workgroup);
		assessment.setGrade(assessmentResource.getGrade());

		assessmentRepository.save(assessment);

	}

}
