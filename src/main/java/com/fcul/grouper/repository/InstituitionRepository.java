package com.fcul.grouper.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Instituition;


@Repository
public interface InstituitionRepository extends CrudRepository<Instituition, Long> {

	public List<Instituition> findByName(String name);

	public Instituition findById(long id);

}
