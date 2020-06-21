package com.fcul.grouper.exceptions.file;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3364009764783627616L;

	public FileNotFoundException() {
		super("The file doesn't exist!");
	}

}
