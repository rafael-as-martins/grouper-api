package com.fcul.grouper.rest.controller;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.exceptions.file.FileException;
import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.File;
import com.fcul.grouper.rest.resources.file.FileMetaResource;
import com.fcul.grouper.rest.resources.file.FileResource;
import com.fcul.grouper.services.interfaces.FileService;
import com.fcul.grouper.services.upload.CSVUploadController;
import com.fcul.grouper.services.upload.FileType;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class FileController {

	@Autowired
	private FileService fileService;

	@Autowired
	private CSVUploadController CSVUploadController;

	@Transactional
	@RequestMapping(path = "/file/{fileId}", method = { RequestMethod.GET })
	public ResponseEntity<Object> getFileContent(@PathVariable final Long fileId) {

		try {

			Resource file = fileService.loadAsResource(fileId);
			String header = new StringBuilder().append("attachment; filename=\"").append(file.getFilename())
					.append("\"").toString();

			return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION, header).body(file);
		} catch (FileException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@RequestMapping(path = "/file/meta/{fileId}", method = { RequestMethod.GET })
	public ResponseEntity<Object> getFileMeta(@PathVariable final Long fileId) {

		File file = fileService.findById(fileId);

		if (file != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new FileResource(file));
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@RequestMapping(path = "/file/{fileId}", method = { RequestMethod.DELETE })
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable final Long fileId) {

		try {
			fileService.delete(fileId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BadRequest e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Transactional
	@RequestMapping(path = "/upload/{workgroupId}/{studentId}", method = { RequestMethod.POST })
	public ResponseEntity<Object> post(@RequestParam MultipartFile file, @PathVariable final Long workgroupId,
			@PathVariable final Long studentId) {

		if (studentId != null || workgroupId != null) {

			try {

				FileMetaResource fileResource = new FileMetaResource(studentId, workgroupId);

				return ResponseEntity.status(HttpStatus.CREATED).body(fileService.insert(file, fileResource));

			} catch (StudentNotFoundException | WorkgroupNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
			}
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("studentId and workgroupId must be filled!");

	}

	@Transactional
	@PreAuthorize("hasRole('ROLE_STAFF')")
	@RequestMapping(path = "/upload/csv", method = { RequestMethod.POST })
	public ResponseEntity<Object> post(@RequestParam MultipartFile file, @RequestParam FileType fileType) {

		try {

			int processed = CSVUploadController.upload(fileType, file);

			return ResponseEntity.status(HttpStatus.CREATED).body(processed);

		} catch (FileUploadException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
