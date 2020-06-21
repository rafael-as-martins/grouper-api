package com.fcul.grouper.model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 
 * Class responsible for containing all the error information, this object is
 * created with the goal of being sent through one ResponseEntity object
 *
 *
 */
@XmlRootElement(name = "error")
public class ErrorResponse {

	private String message;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	public ErrorResponse() {
	}

	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
