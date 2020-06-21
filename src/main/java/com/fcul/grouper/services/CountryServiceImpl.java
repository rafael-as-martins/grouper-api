package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.model.Country;
import com.fcul.grouper.repository.CountryRepository;
import com.fcul.grouper.services.interfaces.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Country findById(long id) {
		return countryRepository.findById(id);
	}

}
