package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.repository.InstituitionRepository;
import com.fcul.grouper.services.interfaces.InstituitionService;

@Service
public class InstituitionServiceImpl implements InstituitionService {

	@Autowired
	private InstituitionRepository instituitionRepository;

	@Override
	public Instituition findById(final long id) {
		return instituitionRepository.findById(id);
	}

}
