package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.model.Configuration;
import com.fcul.grouper.repository.ConfigurationRepository;
import com.fcul.grouper.services.interfaces.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	public Configuration findByProperty(String property) {
		return configurationRepository.findByProperty(property);
	}
	
	

}
