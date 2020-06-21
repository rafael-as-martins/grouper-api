package com.fcul.grouper.services.interfaces;

import com.fcul.grouper.model.Class;
import com.fcul.grouper.model.keys.ClassKey;
import com.fcul.grouper.services.upload.UploadController;

public interface ClassService extends UploadController {

	public Class findById(final ClassKey studentCourseKey);

}
