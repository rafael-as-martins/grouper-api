package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

	public Country findById(final long id);

}
