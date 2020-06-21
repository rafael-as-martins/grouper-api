package com.fcul.grouper.services.upload;

import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.services.interfaces.ClassService;
import com.fcul.grouper.services.interfaces.CourseService;
import com.fcul.grouper.services.interfaces.LectiveCourseService;
import com.fcul.grouper.services.interfaces.ProfessorService;
import com.fcul.grouper.services.interfaces.StudentClassService;
import com.fcul.grouper.services.interfaces.StudentService;

@Service
public class CSVUploadController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private LectiveCourseService lectiveCourseService;

	@Autowired
	private ClassService classService;

	@Autowired
	private StudentClassService studentClassService;

	public static final String COMMA_SEPARATOR = ";";

	public int upload(final FileType fileType, final MultipartFile file) throws FileUploadException {

		int processedEntities = 0;

		switch (fileType) {
		case STUDENT:
			processedEntities = studentService.upload(file);
			break;
		case PROFESSOR:
			processedEntities = professorService.upload(file);
			break;
		case COURSE:
			processedEntities = courseService.upload(file);
			break;
		case LECTIVE_COURSE:
			processedEntities = lectiveCourseService.upload(file);
			break;
		case CLASS:
			processedEntities = classService.upload(file);
			break;
		case STUDENT_CLASS:
			processedEntities = studentClassService.upload(file);
			break;
		default:
			break;
		}

		return processedEntities;
	}

	public static boolean hasValidHeader(final Map<String, String> csvMappedFields, final String header) {

		String[] fields = header.split(CSVUploadController.COMMA_SEPARATOR);

		for (String field : fields) {
			if (!csvMappedFields.containsKey(field)) {
				return false;
			}
		}

		return true;
	}

}
