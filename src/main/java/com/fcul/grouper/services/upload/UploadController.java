package com.fcul.grouper.services.upload;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface UploadController {

	public int upload(final MultipartFile file) throws FileUploadException;

}
