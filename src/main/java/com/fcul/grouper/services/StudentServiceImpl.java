package com.fcul.grouper.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Course;
import com.fcul.grouper.model.Project;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.types.UserType;
import com.fcul.grouper.repository.StudentRepository;
import com.fcul.grouper.rest.resources.course.CourseStudentsResource;
import com.fcul.grouper.rest.resources.student.StudentResource;
import com.fcul.grouper.services.interfaces.CountryService;
import com.fcul.grouper.services.interfaces.InstituitionService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.upload.CSVUploadController;
import com.fcul.grouper.utils.DBFieldMapper;
import com.fcul.grouper.utils.LoginUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CountryService countryService;

	@Autowired
	private InstituitionService instituitionService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private LoginUtil loginUtil;

	private Map<String, String> csvMappedFields;

	public StudentServiceImpl() {
		setCSVMappedFields();
	}

	@Override
	public void updateStudent(StudentResource studentResource, final long id) {

		Student student = studentRepository.findById(id);

		if (student != null) {
			mapResourceIntoEntity(studentResource, student);
		}

	}

	@Override
	public void insertStudent(StudentResource studentResource) {

		Student student = new Student();
		mapResourceIntoEntity(studentResource, student);

		studentRepository.save(student);

	}

	private void mapResourceIntoEntity(StudentResource studentResource, Student student) {

		student.setFirstName(studentResource.getFirstName());
		student.setLastName(studentResource.getLastName());
		student.setDegree(studentResource.getDegree());
		student.setNumber(studentResource.getNumber());
		student.setEmail(studentResource.getEmail());
		student.setEncryptedNic(studentResource.getEncryptedNic());
		student.setPhotoPath(studentResource.getPhotoPath() == null ? "default" : "other");

		if (studentResource.getPassword() != null) {
			student.setPassword(passwordEncoder.encode(studentResource.getPassword()));
		}

		if (studentResource.getCountryId() != null) {
			student.setCountryNic(countryService.findById(studentResource.getCountryId()));
		}

		if (studentResource.getInstituitionId() != null) {
			student.setInstituition(instituitionService.findById(studentResource.getInstituitionId()));
		}
	}

	@Override
	public Student findById(Long id) throws StudentNotFoundException {
		return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
	}

	@Override
	public Set<Course> findCoursesByYear(String year, Long id) {
		return studentRepository.findCoursesByYear(year, id);
	}

	@Override
	public Set<Project> findProjectsByYearAndIdAndCourse(String year, Long id, Long course) {
		return studentRepository.findProjectsByYearAndIdAndCourse(year, id, course);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Student> findStudentsByCourseAndYearAndClass(final CourseStudentsResource courseStudentsResource) {

		String namedQuey = (courseStudentsResource.getWithGroups())
				? Student.QUERY_FIND_STUDENTS_BY_COURSE_AND_YEAR_AND_CLASS_WITH_GROUP
				: Student.QUERY_FIND_STUDENTS_BY_COURSE_AND_YEAR_AND_CLASS;

		Query query = entityManager.createNamedQuery(namedQuey, Student.class);

		query.setParameter(Student.PARAM_YEAR, courseStudentsResource.getYear());
		query.setParameter(Student.PARAM_COURSE, courseStudentsResource.getCourseId());
		query.setParameter(Student.PARAM_SEARCH, DBFieldMapper.getLikeMappedField(courseStudentsResource.getSearch()));
		query.setParameter(Student.PARAM_ORDER_BY_FIELD,
				DBFieldMapper.getOrderField(courseStudentsResource.getOrderField()));

		int pageNumber = courseStudentsResource.getPage();
		int pageQuantity = courseStudentsResource.getPageSize();

		query.setFirstResult((pageNumber - 1) * pageQuantity);
		query.setMaxResults(pageQuantity);

		return query.getResultList();

	}

	@Override
	public Class findClassesByCourseId(long courseId, long studentId) {
		return studentRepository.findClassesByCourseId(courseId, studentId);
	}

	@Override
	public int upload(final MultipartFile file) throws FileUploadException {

		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String header = bf.readLine();

			if (header != null && CSVUploadController.hasValidHeader(csvMappedFields, header)) {

				List<Student> students = new ArrayList<Student>();
				String record;
				String[] recordsFields;

				while ((record = bf.readLine()) != null) {

					recordsFields = record.split(CSVUploadController.COMMA_SEPARATOR);
					int fieldIndex = 0;

					Student student = getOrCreate(recordsFields[2]);
					mapStudentFields(recordsFields, fieldIndex, student);

					students.add(student);

				}

				studentRepository.saveAll(students);
				return students.size();

			}

		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}

		return 0;
	}

	private void mapStudentFields(String[] recordsFields, int fieldIndex, Student student) {
		student.setFirstName(recordsFields[fieldIndex++]);
		student.setLastName(recordsFields[fieldIndex++]);
		student.setEmail(recordsFields[fieldIndex++]);
		student.setDegree(recordsFields[fieldIndex++]);
		student.setNumber(recordsFields[fieldIndex++]);
		student.setPassword(passwordEncoder.encode(recordsFields[fieldIndex++]));
		student.setCountryNic(countryService.findById(Integer.parseInt(recordsFields[fieldIndex++])));
		student.setInstituition(loginUtil.getUserInstituition());
		student.setUserType(UserType.STUDENT);
		student.setEncryptedNic("none");
		student.setPhotoPath("other.png");
	}

	private Student getOrCreate(final String email) {

		Student student = studentRepository.findByEmail(email);

		if (student == null) {
			student = new Student();
		}

		return student;
	}

	private void setCSVMappedFields() {

		csvMappedFields = new HashMap<String, String>();

		csvMappedFields.put("firstname", "firstname");
		csvMappedFields.put("lastname", "lastname");
		csvMappedFields.put("email", "email");
		csvMappedFields.put("degree", "degree");
		csvMappedFields.put("number", "number");
		csvMappedFields.put("password", "password");
		csvMappedFields.put("countryId", "countryId");
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> findBYSearchFilterAndInstituition(String search, Integer page, Integer pageSize) {

		TypedQuery<Student> query = entityManager.createNamedQuery(Student.QUERY_FIND_BY_SEARCH_FILTER_AND_INSTITUITION,
				Student.class);

		query.setParameter(Student.PARAM_INSTITUITION, loginUtil.getUserInstituition().getId());
		query.setParameter(Student.PARAM_SEARCH, DBFieldMapper.getLikeMappedField(search));

		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public Long countBySearchFilterAndInstituition(String search, Integer page, Integer pageSize) {

		TypedQuery<Long> query = entityManager.createNamedQuery(Student.QUERY_COUNT_BY_SEARCH_FILTER_AND_INSTITUITION,
				Long.class);

		query.setParameter(Student.PARAM_INSTITUITION, loginUtil.getUserInstituition().getId());
		query.setParameter(Student.PARAM_SEARCH, DBFieldMapper.getLikeMappedField(search));

		return query.getSingleResult();
	}

}
