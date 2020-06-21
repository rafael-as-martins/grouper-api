package com.fcul.grouper.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

	public Message findById(long id);

	public Set<Long> findIdsByWorkgroup(@Param("workgroupId") Long workgroupId);

}
