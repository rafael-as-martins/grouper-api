package com.fcul.grouper.services.interfaces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.model.LectiveCourse;
import com.fcul.grouper.repository.LectiveCourseRepository;
import com.fcul.grouper.services.upload.CSVUploadController;

@Service
public class LectiveCourseServiceImpl implements LectiveCourseService {

	@Autowired
	private LectiveCourseRepository lectiveCourseRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ProfessorService professorService;

	private Map<String, String> csvMappedFields;

	public LectiveCourseServiceImpl() {
		setCSVMappedFields();
	}

	@Override
	public LectiveCourse findByIdYearAndIdProfessorAndIdCourse(String year, long professor, long course) {
		return lectiveCourseRepository.findByIdYearAndIdProfessorAndIdCourse(year, professor, course);
	}

	@Override
	public int upload(MultipartFile file) throws FileUploadException {

		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String header = bf.readLine();

			if (header != null && CSVUploadController.hasValidHeader(csvMappedFields, header)) {

				List<LectiveCourse> lectiveCourses = new ArrayList<>();
				String record;
				String[] recordsFields;

				while ((record = bf.readLine()) != null) {

					recordsFields = record.split(CSVUploadController.COMMA_SEPARATOR);
					int fieldIndex = 0;

					String year = recordsFields[fieldIndex++];
					Long professorId = Long.parseLong(recordsFields[fieldIndex++]);
					Long courseId = Long.parseLong(recordsFields[fieldIndex]);

					LectiveCourse lectiveCourse = getOrCreate(year, professorId, courseId);
					mapFields(year, professorId, courseId, lectiveCourse);

					lectiveCourses.add(lectiveCourse);

				}

				lectiveCourseRepository.saveAll(lectiveCourses);
				return lectiveCourses.size();

			}

		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}

		return 0;
	}

	private void mapFields(String year, long professorId, long courseId, LectiveCourse lc) {

		courseService.findById(courseId).addLectiveCourse(lc);
		professorService.findById(professorId).addLectiveCourse(lc);

		lc.setYear(year);

	}

	private LectiveCourse getOrCreate(String year, long professorId, long courseId) {

		LectiveCourse lectiveCourse = findByIdYearAndIdProfessorAndIdCourse(year, professorId, courseId);

		if (lectiveCourse == null) {
			lectiveCourse = new LectiveCourse();
		}

		return lectiveCourse;
	}

	private void setCSVMappedFields() {

		csvMappedFields = new HashMap<String, String>();

		csvMappedFields.put("courseId", "courseId");
		csvMappedFields.put("professorId", "professorId");
		csvMappedFields.put("year", "year");
	}

}
