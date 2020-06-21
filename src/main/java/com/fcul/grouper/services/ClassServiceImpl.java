package com.fcul.grouper.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.keys.ClassKey;
import com.fcul.grouper.repository.ClassRepository;
import com.fcul.grouper.services.interfaces.ClassService;
import com.fcul.grouper.services.interfaces.LectiveCourseService;
import com.fcul.grouper.services.upload.CSVUploadController;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private LectiveCourseService lectiveCourseService;

	private Map<String, String> csvMappedFields;

	public ClassServiceImpl() {
		setCSVMappedFields();
	}

	@Override
	public Class findById(ClassKey id) {

		Optional<Class> subject = classRepository.findById(id);
		return (subject.isPresent() ? subject.get() : null);
	}

	@Override
	public int upload(MultipartFile file) throws FileUploadException {

		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String header = bf.readLine();

			if (header != null && CSVUploadController.hasValidHeader(csvMappedFields, header)) {

				List<Class> classes = new ArrayList<>();
				String record;
				String[] recordsFields;

				while ((record = bf.readLine()) != null) {

					recordsFields = record.split(CSVUploadController.COMMA_SEPARATOR);
					int fieldIndex = 0;

					Long courseId = Long.parseLong(recordsFields[fieldIndex++]);
					String year = recordsFields[fieldIndex++];
					Long professorId = Long.parseLong(recordsFields[fieldIndex++]);
					String name = recordsFields[fieldIndex];

					Class c = getOrCreate(year, name, courseId);

					mapFields(courseId, year, professorId, name, c);

					classes.add(c);

				}

				classRepository.saveAll(classes);
				return classes.size();

			}

		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}

		return 0;
	}

	private void mapFields(Long courseId, String year, Long professorId, String name, Class c) {
		c.setLectiveCourse(lectiveCourseService.findByIdYearAndIdProfessorAndIdCourse(year, professorId, courseId));
		c.setName(name);
	}

	private Class getOrCreate(String year, String name, long courseId) {

		Class c = findById(new ClassKey(courseId, name, year));

		if (c == null) {
			c = new Class();
		}

		return c;
	}

	private void setCSVMappedFields() {

		csvMappedFields = new HashMap<String, String>();

		csvMappedFields.put("courseId", "courseId");
		csvMappedFields.put("year", "year");
		csvMappedFields.put("professorId", "professorId");
		csvMappedFields.put("name", "name");
	}

}
