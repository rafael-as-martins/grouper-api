package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

	public Login findByEmail(final String email);

}
