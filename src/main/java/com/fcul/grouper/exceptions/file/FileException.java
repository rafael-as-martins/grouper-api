package com.fcul.grouper.exceptions.file;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 6696414062670781091L;

	public FileException(String message) {
		super(message);
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

}
