package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.File;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {

	public File findById(final long id);

	public File getOne(final Long id);

	public File findByUrl(final String url);

	public Set<File> findByWorkgroupId(final Long workgroupId);

}
