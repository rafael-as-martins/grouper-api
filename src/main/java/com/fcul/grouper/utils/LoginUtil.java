package com.fcul.grouper.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fcul.grouper.config.security.UserPrincipal;
import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.services.interfaces.ProfessorService;
import com.fcul.grouper.services.interfaces.StaffService;
import com.fcul.grouper.services.interfaces.StudentService;

@Service
public class LoginUtil {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private StaffService staffService;

	public Instituition getUserInstituition() {

		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
			return staffService.findById(user.getId()).getInstituition();
		} else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROFESSOR"))) {
			return professorService.findById(user.getId()).getInstituition();
		} else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
			return studentService.findById(user.getId()).getInstituition();
		}

		return null;
	}

}
