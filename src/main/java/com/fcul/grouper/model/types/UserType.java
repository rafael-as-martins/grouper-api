package com.fcul.grouper.model.types;

public enum UserType {

	PROFESSOR,

	STUDENT,

	STAFF;

	public boolean isProfessor() {
		return this == PROFESSOR;
	}

	public boolean isStudent() {
		return this == STUDENT;
	}
	
	public boolean isStaff() {
		return this == STAFF;
	}
	
}
