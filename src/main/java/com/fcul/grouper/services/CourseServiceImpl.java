package com.fcul.grouper.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.repository.CourseRepository;
import com.fcul.grouper.services.interfaces.CourseService;
import com.fcul.grouper.services.upload.CSVUploadController;
import com.fcul.grouper.utils.DBFieldMapper;
import com.fcul.grouper.utils.LoginUtil;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private LoginUtil loginUtil;

	private Map<String, String> csvMappedFields;

	public CourseServiceImpl() {
		setCSVMappedFields();
	}

	@Override
	public Course findById(final long id) {
		return courseRepository.findById(id);
	}

	@Override
	public int upload(MultipartFile file) throws FileUploadException {

		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String header = bf.readLine();

			if (header != null && CSVUploadController.hasValidHeader(csvMappedFields, header)) {

				List<Course> courses = new ArrayList<>();
				String record;
				String[] recordsFields;

				while ((record = bf.readLine()) != null) {

					recordsFields = record.split(CSVUploadController.COMMA_SEPARATOR);
					int fieldIndex = 0;

					Course course = getOrCreate(Long.parseLong(recordsFields[0]));
					mapFields(recordsFields, fieldIndex, course);

					courses.add(course);

				}

				courseRepository.saveAll(courses);
				return courses.size();

			}

		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}

		return 0;
	}

	private void mapFields(String[] recordsFields, int fieldIndex, Course course) {
		course.setId(Long.parseLong(recordsFields[fieldIndex++]));
		course.setName(recordsFields[fieldIndex]);
		course.setInstituition(loginUtil.getUserInstituition());
	}

	private Course getOrCreate(final Long id) {

		Course course = findById(id);

		if (course == null) {
			course = new Course();
		}

		return course;
	}

	private void setCSVMappedFields() {

		csvMappedFields = new HashMap<String, String>();

		csvMappedFields.put("id", "id");
		csvMappedFields.put("name", "name");
	}

	@Override
	public Set<Project> findProjectsByCourseIdAndYear(long courseId, String year) {
		return courseRepository.findProjectsByCourseIdAndYear(courseId, year);
	}

	@Override
	public List<Course> findByInstituitionAndName(String name, Integer page, Integer pageSize) {

		TypedQuery<Course> query = entityManager.createNamedQuery(Course.QUERY_FIND_BY_NAME_AND_INSTITUITION,
				Course.class);

		query.setParameter(Course.PARAM_INSTITUITION, loginUtil.getUserInstituition().getId());
		query.setParameter(Course.PARAM_NAME, DBFieldMapper.getLikeMappedField(name));

		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();

	}

	@Override
	public Long countByInstituitionAndName(String name, Integer page, Integer pageSize) {

		TypedQuery<Long> query = entityManager.createNamedQuery(Course.QUERY_COUNT_BY_NAME_AND_INSTITUITION,
				Long.class);

		query.setParameter(Course.PARAM_INSTITUITION, loginUtil.getUserInstituition().getId());
		query.setParameter(Course.PARAM_NAME, DBFieldMapper.getLikeMappedField(name));

		return query.getSingleResult();

	}

}
