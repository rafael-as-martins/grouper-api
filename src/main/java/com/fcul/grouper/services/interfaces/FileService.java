package com.fcul.grouper.services.interfaces;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.exceptions.file.FileException;
import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.File;
import com.fcul.grouper.rest.resources.file.FileMetaResource;

public interface FileService {

	public File findById(final long id);

	public void init();

	public FileMetaResource insert(final MultipartFile file, final FileMetaResource fileResource)
			throws StudentNotFoundException, WorkgroupNotFoundException, IOException;

	public Path load(String filename);

	public Resource loadAsResource(final Long fileId) throws FileException;

	public Set<File> findByWorkgroupId(final Long workgroupId);

	public void delete(final Long fileId) throws IOException;

}
