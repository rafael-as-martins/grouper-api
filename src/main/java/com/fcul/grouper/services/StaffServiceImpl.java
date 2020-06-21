package com.fcul.grouper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcul.grouper.model.Staff;
import com.fcul.grouper.repository.StaffRepository;
import com.fcul.grouper.services.interfaces.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public Staff findById(final long id) {
		return staffRepository.findById(id);
	}

}
