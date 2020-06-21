package com.fcul.grouper.services;

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

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.keys.ClassKey;
import com.fcul.grouper.services.interfaces.ClassService;
import com.fcul.grouper.services.interfaces.StudentClassService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.upload.CSVUploadController;

@Service
public class StudentClassServiceImpl implements StudentClassService {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassService classService;

	private Map<String, String> csvMappedFields;

	public StudentClassServiceImpl() {
		setCSVMappedFields();
	}

	@Override
	public int upload(MultipartFile file) throws FileUploadException {

		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			String header = bf.readLine();

			if (header != null && CSVUploadController.hasValidHeader(csvMappedFields, header)) {

				Map<Student, List<Class>> studentClasses = new HashMap<>();
				String record;
				String[] recordsFields;

				while ((record = bf.readLine()) != null) {

					recordsFields = record.split(CSVUploadController.COMMA_SEPARATOR);
					int fieldIndex = 0;

					Student student = studentService.findById(Long.parseLong(recordsFields[fieldIndex++]));

					addClass(studentClasses, recordsFields, fieldIndex, student);

				}

				saveAssociations(studentClasses);

			}

		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}

		return 0;
	}

	private void saveAssociations(Map<Student, List<Class>> studentClasses) {
		for (Student student : studentClasses.keySet()) {
			for (Class c : studentClasses.get(student)) {
				student.addClass(c);
			}
			studentService.save(student);
		}
	}

	private void addClass(Map<Student, List<Class>> studentClasses, String[] recordsFields, int fieldIndex,
			Student student) {
		Long courseId = Long.parseLong(recordsFields[fieldIndex++]);
		String name = recordsFields[fieldIndex++];
		String year = recordsFields[fieldIndex];

		Class c = classService.findById(new ClassKey(courseId, name, year));
		List<Class> classes = studentClasses.getOrDefault(student, new ArrayList<Class>());
		classes.add(c);

		studentClasses.put(student, classes);
	}

	private void setCSVMappedFields() {

		csvMappedFields = new HashMap<String, String>();

		csvMappedFields.put("studentId", "studentId");
		csvMappedFields.put("courseId", "courseId");
		csvMappedFields.put("name", "name");
		csvMappedFields.put("year", "year");
	}
}
