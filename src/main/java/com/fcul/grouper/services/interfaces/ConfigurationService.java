package com.fcul.grouper.services.interfaces;

import com.fcul.grouper.model.Configuration;

public interface ConfigurationService {
	
	public Configuration findByProperty(final String property);

}
