package com.fcul.grouper.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.course.CourseNotFoundException;
import com.fcul.grouper.exceptions.professor.ProfessorNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Feedback;
import com.fcul.grouper.repository.FeedbackRepository;
import com.fcul.grouper.rest.resources.FeedbackResource;
import com.fcul.grouper.services.interfaces.FeedbackService;
import com.fcul.grouper.services.interfaces.ProfessorService;
import com.fcul.grouper.services.interfaces.StepService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class FeedbackSeviceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private WorkgroupService workgroupService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private StepService stepService;

	@Override
	public void updateFeedback(FeedbackResource feedbackResource, long id) {

		Feedback feedback = feedbackRepository.findById(id);

		if (feedback != null) {

			feedback.setType(feedbackResource.getType());
			feedback.setGrade(feedbackResource.getGrade());
			feedback.setContent(feedbackResource.getContent());

		}

	}

	public Feedback findById(final long id) {
		return feedbackRepository.findById(id);
	}

	@Override
	public void createFeedback(FeedbackResource feedbackResource)
			throws WorkgroupNotFoundException, ProfessorNotFoundException, CourseNotFoundException {

		Feedback feedback = mapFeedbackResourceIntoFeedback(feedbackResource);

		workgroupService.findById(feedbackResource.getWorkgroupId()).addFeedback(feedback);

		professorService.findById(feedbackResource.getProfessorId()).addFeedback(feedback);

		feedback.setStep(stepService.findByIdProjectAndIdStepOrder(feedbackResource.getProjectId(),
				feedbackResource.getStepOrder()));

		feedbackRepository.save(feedback);

	}

	private Feedback mapFeedbackResourceIntoFeedback(FeedbackResource feedbackResource) {

		Feedback feedback = new Feedback();

		feedback.setType(feedbackResource.getType());
		feedback.setGrade(feedbackResource.getGrade());
		feedback.setContent(feedbackResource.getContent());

		return feedback;

	}

	@Override
	public Set<Feedback> findByWorkgroupId(Long workgroupId) {
		return feedbackRepository.findByWorkgroupId(workgroupId);
	}

}
