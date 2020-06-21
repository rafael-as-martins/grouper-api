package com.fcul.grouper.exceptions.task;

public class TaskNotFoundException extends Exception {

	private static final long serialVersionUID = 3364009764783627616L;

	public TaskNotFoundException() {
		super("The task with the given id, doesn't exist!");
	}

}
