package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.repository.DoubtRepository;
import com.fcul.grouper.rest.resources.doubt.DoubtResource;
import com.fcul.grouper.services.interfaces.DoubtService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class DoubtServiceImpl implements DoubtService {

	@Autowired
	private DoubtRepository doubtRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private WorkgroupService workgroupService;

	@Override
	public Doubt findById(long id) {
		return doubtRepository.findById(id);
	}

	@Override
	public void insert(DoubtResource doubtResource) throws StudentNotFoundException, WorkgroupNotFoundException {

		Doubt doubt = mapResourceIntoEntity(doubtResource);

		studentService.findById(doubtResource.getStudentId()).addDoubt(doubt);
		workgroupService.findById(doubtResource.getWorkgroupId()).addDoubt(doubt);

		doubtRepository.save(doubt);

	}

	private Doubt mapResourceIntoEntity(final DoubtResource doubtResource) {

		Doubt doubt = new Doubt();

		doubt.setCreationDate(new Date());
		doubt.setQuestion(doubtResource.getQuestion());

		return doubt;

	}

	@Override
	public void addAnswer(DoubtResource doubtResource) {

		Doubt doubt = doubtRepository.getOne(doubtResource.getId());

		doubt.setAnswerDate(new Date());
		doubt.setAnswer(doubtResource.getAnswer());

		doubtRepository.save(doubt);

	}

	@Override
	public Set<Doubt> findByWorkgroupId(long workgroupId) {
		return doubtRepository.findByWorkgroupId(workgroupId);
	}

	@Override
	public Set<Doubt> findByProjectId(long projectId) {
		return doubtRepository.findByProjectId(projectId);
	}

}
