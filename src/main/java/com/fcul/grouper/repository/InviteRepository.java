package com.fcul.grouper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcul.grouper.model.Invite;

@Repository
public interface InviteRepository extends CrudRepository<Invite, Long> {

	public Invite findById(final long id);

}
