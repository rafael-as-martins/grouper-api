package com.fcul.grouper.exceptions.lectiveCourse;

public class LectiveCourseNotFoundException extends Exception {

	private static final long serialVersionUID = 5218005014254102018L;

	public LectiveCourseNotFoundException() {
		super("No lective course was found with the given id!");
	}

}
