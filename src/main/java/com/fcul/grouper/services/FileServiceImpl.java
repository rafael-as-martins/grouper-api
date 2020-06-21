package com.fcul.grouper.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fcul.grouper.config.StorageProperties;
import com.fcul.grouper.exceptions.file.FileException;
import com.fcul.grouper.exceptions.file.FileNotFoundException;
import com.fcul.grouper.exceptions.student.StudentNotFoundException;
import com.fcul.grouper.exceptions.workgroup.WorkgroupNotFoundException;
import com.fcul.grouper.model.File;
import com.fcul.grouper.repository.FileRepository;
import com.fcul.grouper.rest.resources.file.FileMetaResource;
import com.fcul.grouper.rest.resources.file.FileNameAwareByteArrayResource;
import com.fcul.grouper.services.interfaces.FileService;
import com.fcul.grouper.services.interfaces.StudentService;
import com.fcul.grouper.services.interfaces.WorkgroupService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private WorkgroupService workgroupService;

	private Path rootLocation;

	private boolean dbStorage;

	@Autowired
	public FileServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
		this.dbStorage = properties.isDb();
	}

	@Override
	@PostConstruct
	public void init() {

		try {

			Files.createDirectories(rootLocation);

		} catch (IOException e) {
			throw new FileException("Failded to read stored files", e);
		}
	}

	@Override
	public File findById(final long id) throws FileNotFoundException {
		return fileRepository.findById(id);
	}

	@Override
	public void delete(final Long fileId) throws IOException {

		File file = findById(fileId);
		
		if (file.getData() == null) {
			Files.delete(this.rootLocation.resolve(file.getUrl()));
		}

		fileRepository.delete(file);

	}

	@Override
	public FileMetaResource insert(final MultipartFile file, final FileMetaResource fileResource)
			throws StudentNotFoundException, WorkgroupNotFoundException, IOException {

		Long workgroupId = fileResource.getWorkgroupId();
		Long studentId = fileResource.getStudentId();
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		Long fileId = null;

		String url = new StringBuilder().append(workgroupId).append("/").append(filename).toString();

		validateFile(file, filename);

		if (dbStorage) {
			fileId = createEntityFromResource(file, workgroupId, studentId, url);
		} else {

			fileId = storeOnFileSystem(file, workgroupId, studentId, fileId, url);
		}

		return mapToResource(filename, fileId, file, studentId, workgroupId);
	}

	private Long storeOnFileSystem(final MultipartFile file, Long workgroupId, Long studentId, Long fileId, String url)
			throws WorkgroupNotFoundException {

		try (InputStream inputStream = file.getInputStream()) {

			Files.createDirectories(Paths.get(rootLocation.toString(), workgroupId.toString()));

			if (fileRepository.findByUrl(url) == null) {
				fileId = createEntityFromResource(file, workgroupId, studentId, url);
			}

			Files.copy(inputStream, this.rootLocation.resolve(url), StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new FileException("Failed to store file " + url, e);
		}

		return fileId;
	}

	private FileMetaResource mapToResource(final String filename, final Long fileId, final MultipartFile file,
			final Long studentId, final Long workgroupId) {

		FileMetaResource resource = new FileMetaResource(studentId, workgroupId);

		resource.setName(filename);
		resource.setSize(file.getSize());
		resource.setType(file.getContentType());
		resource.setFileId(fileId);

		return resource;

	}

	private Long createEntityFromResource(final MultipartFile multipartFile, Long workgroupId, Long studentId,
			String url) throws StudentNotFoundException, WorkgroupNotFoundException, IOException {

		File file = new File();

		studentService.findById(studentId).addFile(file);
		workgroupService.findById(workgroupId).addFile(file);
		file.setUrl(url);

		if (dbStorage) {
			file.setData(multipartFile.getBytes());
		}

		fileRepository.save(file);

		return file.getId();
	}

	private void validateFile(MultipartFile file, String filename) {
		if (file.isEmpty()) {
			throw new FileException("Failed to store empty file " + filename);
		}

		if (filename.contains("..")) {
			throw new FileException("Cannot store file with relative path outside current directory " + filename);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(final Long fileId) {

		try {

			Optional<File> file = fileRepository.findById(fileId);
			Resource resource = null;

			if (!file.isPresent()) {
				throw new FileException("The specified file doesn't exist!");
			}

			String url = file.get().getUrl();
			
			if (file.get().getData() == null) {
				Path path = load(url);
				resource = new UrlResource(path.toUri());
			} else {
				String filename = url.substring(url.lastIndexOf("/"), url.length());
				resource = new FileNameAwareByteArrayResource(file.get().getData(), filename);
			}
			
			

			if (resource.exists() || resource.isReadable()) {
				return resource;
			}

			throw new FileException("Could not read file from " + fileId);

		} catch (MalformedURLException e) {
			throw new FileException("Error while generating file URL", e);
		}

	}

	@Override
	public Set<File> findByWorkgroupId(Long workgroupId) {
		return fileRepository.findByWorkgroupId(workgroupId);
	}

}
