package com.fcul.grouper.exceptions.professor;

public class ProfessorNotFoundException extends Exception{

	private static final long serialVersionUID = 3364009764783627616L;

	
	public ProfessorNotFoundException() {
		super("The professor with the given id, doesn't exist!");
	}
	
	
}
