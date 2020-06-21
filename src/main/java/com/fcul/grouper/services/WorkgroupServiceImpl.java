package com.fcul.grouper.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.Doubt;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.model.keys.ClassKey;
import com.fcul.grouper.repository.WorkgroupRepository;
import com.fcul.grouper.rest.resources.workgroup.WorkgroupAssociationResource;
import com.fcul.grouper.services.interfaces.ClassService;
import com.fcul.grouper.services.interfaces.MessageService;
import com.fcul.grouper.services.interfaces.ProjectService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class WorkgroupServiceImpl implements WorkgroupService {

	@Autowired
	private WorkgroupRepository workgroupRepository;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassService classService;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Workgroup> findByStudentId(final long studentId) {
		return workgroupRepository.findByStudents_Id(studentId);
	}

	@Override
	public List<Workgroup> findByProjectId(final long projectId) {
		return workgroupRepository.findByProject_Id(projectId);
	}

	@Override
	public Workgroup findById(Long id) throws WorkgroupNotFoundException {

		Optional<Workgroup> workgroup = workgroupRepository.findById(id);

		if (!workgroup.isPresent()) {
			throw new WorkgroupNotFoundException();
		}

		return workgroup.get();
	}

	@Override
	public long createWorkgroup(Long projectId, Long studentId, Long courseId, String name, String year) {

		Workgroup workgroup = new Workgroup();

		projectService.findById(projectId).addWorkgroup(workgroup);
		studentService.findById(studentId).addWorkgroup(workgroup);

		workgroupRepository.save(workgroup);

		if (courseId != null && name != null && year != null) {

			classService.findById(new ClassKey(courseId, name, year)).addWorkgroup(workgroup);
			workgroupRepository.save(workgroup);
		}

		return workgroup.getId();
	}

	@Override
	public long addStudentAssociation(final WorkgroupAssociationResource workgroupAssociationResource)
			throws WorkgroupNotFoundException {

		Long workgroupId = workgroupAssociationResource.getWorkgroupId();
		Long studentId = workgroupAssociationResource.getStudentId();
		Long projectId = workgroupAssociationResource.getProjectId();
		Long courseId = workgroupAssociationResource.getCourseId();
		String name = workgroupAssociationResource.getClassName();
		String year = workgroupAssociationResource.getYear();

		if (workgroupId == null) {
			return createWorkgroup(projectId, studentId, courseId, name, year);
		} else {
			Workgroup workgroup = findById(workgroupId);
			studentService.findById(studentId).addWorkgroup(workgroup);

		}

		return workgroupId;

	}

	@Override
	public Set<Doubt> findDoubtsByWorkgroupId(Long id) {
		return workgroupRepository.findDoubtsByWorkgroupId(id);
	}

	@Override
	public Set<Long> findMessagesByid(Long id) {
		return messageService.findIdsByWorkgroup(id);
	}

	@Override
	public void removeStudentFromWorkgroupByProjectId(final long projectId) {

		Query query = entityManager.createNativeQuery(
				"DELETE FROM student_workgroup sw USING project_workgroup pw WHERE pw.workgroup = sw.workgroup AND pw.project = :"
						+ Project.PARAM_PROJECT_ID);

		query.setParameter(Project.PARAM_PROJECT_ID, projectId);

		query.executeUpdate();

	}

	@Override
	public int removeStudentFromWorkgroupByWorkgroupIdAndStudentId(long workgroupId, long studentId) {

		Query query = entityManager.createNativeQuery(
				"DELETE FROM student_workgroup sw WHERE sw.workgroup = :workgroupId AND sw.student = :studentId");

		query.setParameter("workgroupId", workgroupId);
		query.setParameter("studentId", studentId);

		return query.executeUpdate();

	}

}
