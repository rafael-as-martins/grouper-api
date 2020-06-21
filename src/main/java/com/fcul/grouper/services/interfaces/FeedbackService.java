package com.fcul.grouper.services.interfaces;

import java.util.Set;

import com.fcul.grouper.exceptions.course.CourseNotFoundException;
import com.fcul.grouper.exceptions.professor.ProfessorNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Feedback;
import com.fcul.grouper.rest.resources.FeedbackResource;

public interface FeedbackService {

	public void updateFeedback(final FeedbackResource feedbackResource, final long id);

	public void createFeedback(final FeedbackResource feedbackResource)
			throws WorkgroupNotFoundException, ProfessorNotFoundException, CourseNotFoundException;

	public Set<Feedback> findByWorkgroupId(final Long workgroupId);
}
