package com.fcul.grouper.exceptions.project;

public class ProjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5507000860406473438L;

	
	public ProjectNotFoundException() {
		super("No project found");
	}
	
}
