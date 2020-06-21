package com.fcul.grouper.exceptions.workgroup;

public class WorkgroupNotFoundException extends Exception {

	private static final long serialVersionUID = 3364009764783627616L;

	public WorkgroupNotFoundException() {
		super("The workgroup with the given id, doesn't exist!");
	}

}
