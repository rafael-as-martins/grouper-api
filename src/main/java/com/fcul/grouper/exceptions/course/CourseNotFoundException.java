package com.fcul.grouper.exceptions.course;

public class CourseNotFoundException extends Exception{

	private static final long serialVersionUID = 3364009764783627616L;

	
	public CourseNotFoundException() {
		super("The course with the given id, doesn't exist!");
	}
	
	
}
