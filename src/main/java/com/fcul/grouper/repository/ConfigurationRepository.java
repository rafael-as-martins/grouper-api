package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Configuration;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Long>{

	
	public Configuration findByProperty(final String property);
	
}
