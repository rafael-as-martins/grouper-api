package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.302+0000")
@StaticMetamodel(Staff.class)
public class Staff_ {
	public static volatile SingularAttribute<Staff, Long> id;
	public static volatile SingularAttribute<Staff, String> firstName;
	public static volatile SingularAttribute<Staff, String> lastName;
	public static volatile SingularAttribute<Staff, String> fullName;
	public static volatile SingularAttribute<Staff, String> email;
	public static volatile SingularAttribute<Staff, Instituition> instituition;
}
