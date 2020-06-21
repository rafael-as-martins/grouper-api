package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.rest.resources.CountryResource;
import com.fcul.grouper.services.interfaces.CountryService;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class CountryController {

	private static final Logger log = Logger.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	@GetMapping("/country/{id}")
	public ResponseEntity<CountryResource> get(@PathVariable final long id) {

		log.debug("/country/" + id + " HTTP GET METHOD REQUESTED");

		return new ResponseEntity<CountryResource>(new CountryResource(countryService.findById(id)), HttpStatus.OK);

	}

}
