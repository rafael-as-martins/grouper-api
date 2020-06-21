package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

	public Staff findById(long id);
	
	public Staff findByEmail(final String email);
}
