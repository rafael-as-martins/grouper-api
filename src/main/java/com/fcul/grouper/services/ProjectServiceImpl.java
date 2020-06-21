package com.fcul.grouper.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.exceptions.lectiveCourse.LectiveCourseNotFoundException;
import com.fcul.grouper.exceptions.project.ProjectNotFoundException;
import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Workgroup;
import com.fcul.grouper.repository.ProjectRepository;
import com.fcul.grouper.rest.resources.project.ProjectResource;
import com.fcul.grouper.rest.resources.project.ProjectWorkgroups;
import com.fcul.grouper.services.interfaces.LectiveCourseService;
import com.fcul.grouper.services.interfaces.ProjectService;
import com.fcul.grouper.services.interfaces.WorkgroupService;
import com.fcul.grouper.utils.DBFieldMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private LectiveCourseService lectiveCourseService;

	@Autowired
	private WorkgroupService workgroupService;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Project findById(long id) {
		return projectRepository.findById(id);
	}

	@Override
	public long createProject(final ProjectResource projectResource) throws LectiveCourseNotFoundException {

		Project project = mapFromProjectResourceToProject(projectResource);

		String year = projectResource.getYear();
		Long course = projectResource.getCourseId();
		Long professor = projectResource.getProfessorId();

		LectiveCourse lc = lectiveCourseService.findByIdYearAndIdProfessorAndIdCourse(year, professor, course);

		if (lc == null) {
			throw new LectiveCourseNotFoundException();
		}

		lc.addProject(project);

		projectRepository.save(project);

		return project.getId();

	}

	private Project mapFromProjectResourceToProject(final ProjectResource projectResource) {

		Project project = new Project();

		project.setName(projectResource.getName());
		project.setStartDate(projectResource.getStartDate());
		project.setEndDate(projectResource.getEndDate());
		project.setMinElems(projectResource.getMinElems());
		project.setMaxElems(projectResource.getMaxElems());
		project.setClassRestriction(projectResource.getClassRestriction());
		project.setStatus(projectResource.getStatus());
		project.setDescription(projectResource.getDescription());

		return project;

	}

	@Override
	public void updateEndDate(final Long projectId, final Date endDate) {

		Project project = projectRepository.getOne(projectId);

		project.setEndDate(endDate);

		projectRepository.save(project);

	}

	@Override
	public void updateStatus(Long projectId, Boolean status) {
		Project project = projectRepository.getOne(projectId);

		project.setStatus(status);

		projectRepository.save(project);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Workgroup> findWorkgroupsByProjectIdWithPagination(ProjectWorkgroups projectWorkgroups) {

		Query query = entityManager.createNamedQuery(Project.QUERY_FIND_WORKGROUPS_BY_PROJECT_ID, Workgroup.class);

		query.setParameter(Project.PARAM_PROJECT_ID, projectWorkgroups.getProjectId());
		query.setParameter(Project.PARAM_SEARCH, DBFieldMapper.getLikeMappedField(projectWorkgroups.getSearch()));
		query.setParameter(Project.PARAM_ORDER_BY_FIELD,
				DBFieldMapper.getOrderField(projectWorkgroups.getOrderField()));

		// TODO: Switch for Criteria Builder in order to perform it!
		// query.setParameter(Project.PARAM_ORDER, projectWorkgroups.getOrder());

		int page = projectWorkgroups.getPage();
		int pageSize = projectWorkgroups.getPageSize();

		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public void delete(Long projectId) {

		workgroupService.removeStudentFromWorkgroupByProjectId(projectId);
		projectRepository.deleteById(projectId);
	}

	@Override
	public void update(ProjectResource projectResource) {

		Optional<Project> optional = projectRepository.findById(projectResource.getId());

		if (!optional.isPresent()) {
			throw new ProjectNotFoundException();
		}

		Project project = optional.get();

		project.setName(projectResource.getName());
		project.setStartDate(projectResource.getStartDate());
		project.setEndDate(projectResource.getEndDate());
		project.setMinElems(projectResource.getMinElems());
		project.setMaxElems(projectResource.getMaxElems());
		project.setClassRestriction(projectResource.getClassRestriction());
		project.setStatus(projectResource.getStatus());
		project.setDescription(projectResource.getDescription());

		projectRepository.save(project);

	}

}
