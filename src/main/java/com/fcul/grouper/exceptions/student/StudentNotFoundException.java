package com.fcul.grouper.exceptions.student;

public class StudentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3364009764783627616L;

	
	public StudentNotFoundException() {
		super("The student with the given id, doesn't exist!");
	}
	
	
}
