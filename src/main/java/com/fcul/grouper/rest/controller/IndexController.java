package com.fcul.grouper.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/hal+json")
public class IndexController {

	@RequestMapping(path = "/api/home", method = { RequestMethod.GET })
	public ResponseEntity<Object> welcome() {
		return ResponseEntity.status(HttpStatus.OK).body("Welcome to grouper API");

	}

	@RequestMapping(path = "/loaderio-ef198b2a675758a74ff061c1e98441e0/", method = { RequestMethod.GET })
	public ResponseEntity<Object> loadTests() {
		return ResponseEntity.status(HttpStatus.OK).body("loaderio-ef198b2a675758a74ff061c1e98441e0");

	}

}
