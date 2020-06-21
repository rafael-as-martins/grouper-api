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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Professor;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.types.UserType;
import com.fcul.grouper.repository.ProfessorRepository;
import com.fcul.grouper.rest.resources.professor.ProfessorResource;
import com.fcul.grouper.services.interfaces.CountryService;
import com.fcul.grouper.services.interfaces.ProfessorService;
import com.fcul.grouper.services.upload.CSVUploadController;
import com.fcul.grouper.utils.DBFieldMapper;
import com.fcul.grouper.utils.LoginUtil;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private CountryService countryService;

	@Autowired
	private LoginUtil loginUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EntityManager entityManager;

	private Map<String, String> csvMappedFields;

	public ProfessorServiceImpl() {
		setCSVMappedFields();
	}

	@Override
	public void updateProfessor(ProfessorResource professorResource, long id) {

		Professor professor = professorRepository.findById(id);

		if (professor != null) {

			professor.setFirstName(professorResource.getFirstName());
			professor.setLastName(professorResource.getLastName());
			professor.setNumber(professorResource.getNumber());
			professor.setEmail(professorResource.getEmail());
			professor.setEncryptedNic(professorResource.getEncryptedNic());
			professor.setPhotoPath(professorResource.getPhotoPath());

			if (professorResource.getPassword() != null) {
				professor.setPassword(passwordEncoder.encode(professorResource.getPassword()));
			}

		}

	}

	@Override
	public Professor findById(final long id) {

		return professorRepository.findById(id);
	}

	@Override
	public Set<Course> findCoursesByYear(String year, Long professorId) {
		return professorRepository.findCoursesByYear(year, professorId);
	}

	@Override
	public Set<Project> findProjectsByYearAndIdAndCourse(String year, Long professorId, Long course) {
		return professorRepository.findProjectsByYearAndIdAndCourse(year, professorId, course);
	}

	@Override
	public int upload(final MultipartFile file) throws FileUploadException {

		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String header = bf.readLine();

			if (header != null && CSVUploadController.hasValidHeader(csvMappedFields, header)) {

				List<Professor> professors = new ArrayList<>();
				String record;
				String[] recordsFields;

				while ((record = bf.readLine()) != null) {

					recordsFields = record.split(CSVUploadController.COMMA_SEPARATOR);
					int fieldIndex = 0;

					Professor professor = getOrCreate(recordsFields[2]);
					mapFields(recordsFields, fieldIndex, professor);

					professors.add(professor);

				}

				professorRepository.saveAll(professors);
				return professors.size();

			}

		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}

		return 0;
	}

	private void mapFields(String[] recordsFields, int fieldIndex, Professor professor) {
		professor.setFirstName(recordsFields[fieldIndex++]);
		professor.setLastName(recordsFields[fieldIndex++]);
		professor.setEmail(recordsFields[fieldIndex++]);
		professor.setNumber(recordsFields[fieldIndex++]);
		professor.setPassword(passwordEncoder.encode(recordsFields[fieldIndex++]));
		professor.setCountryNic(countryService.findById(Integer.parseInt(recordsFields[fieldIndex])));
		professor.setInstituition(loginUtil.getUserInstituition());
		professor.setUserType(UserType.PROFESSOR);
	}

	private Professor getOrCreate(final String email) {

		Professor professor = professorRepository.findByEmail(email);

		if (professor == null) {
			professor = new Professor();
		}

		return professor;
	}

	private void setCSVMappedFields() {

		csvMappedFields = new HashMap<String, String>();

		csvMappedFields.put("firstname", "firstname");
		csvMappedFields.put("lastname", "lastname");
		csvMappedFields.put("email", "email");
		csvMappedFields.put("number", "number");
		csvMappedFields.put("password", "password");
		csvMappedFields.put("countryId", "countryId");
	}

	@Override
	public List<Professor> findBySearchFilterAndInstituition(String search, Integer page, Integer pageSize) {

		TypedQuery<Professor> query = entityManager
				.createNamedQuery(Professor.QUERY_FIND_BY_SEARCH_FILTER_AND_INSTITUIION, Professor.class);

		query.setParameter(Student.PARAM_INSTITUITION, loginUtil.getUserInstituition().getId());
		query.setParameter(Student.PARAM_SEARCH, DBFieldMapper.getLikeMappedField(search));

		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();

	}

	@Override
	public Long countBySearchFilterAndInstituition(String search, Integer page, Integer pageSize) {

		TypedQuery<Long> query = entityManager.createNamedQuery(Professor.QUERY_COUNT_BY_SEARCH_FILTER_AND_INSTITUIION,
				Long.class);

		query.setParameter(Student.PARAM_INSTITUITION, loginUtil.getUserInstituition().getId());
		query.setParameter(Student.PARAM_SEARCH, DBFieldMapper.getLikeMappedField(search));

		return query.getSingleResult();

	}

}
