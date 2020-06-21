package com.fcul.grouper.rest.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcul.grouper.model.Configuration;
import com.fcul.grouper.rest.resources.ConfigurationResource;
import com.fcul.grouper.services.ConfigurationServiceImpl;

@RestController
@RequestMapping(path = "/api", produces = "application/hal+json")
public class ConfigurationController {

	private static final Logger log = Logger.getLogger(ConfigurationController.class);

	@Autowired
	private ConfigurationServiceImpl configurationServiceImpl;

	@GetMapping("/configuration/{property}")
	public ResponseEntity<Object> get(@PathVariable String property) {

		Configuration configuration = configurationServiceImpl.findByProperty(property);

		if (configuration != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ConfigurationResource(configuration));
		}

		log.info("Configuration with property name " + property + " not found");

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
