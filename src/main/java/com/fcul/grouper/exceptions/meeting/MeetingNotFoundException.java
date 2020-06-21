package com.fcul.grouper.exceptions.meeting;

public class MeetingNotFoundException extends Exception{

	private static final long serialVersionUID = 3364009764783627616L;

	
	public MeetingNotFoundException() {
		super("The meeting with the given id, doesn't exist!");
	}
	
	
}
