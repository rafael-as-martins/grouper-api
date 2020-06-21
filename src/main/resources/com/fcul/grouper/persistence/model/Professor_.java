package com.fcul.grouper.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T13:47:48.271+0000")
@StaticMetamodel(Professor.class)
public class Professor_ {
	public static volatile SingularAttribute<Professor, Long> id;
	public static volatile SingularAttribute<Professor, String> firstName;
	public static volatile SingularAttribute<Professor, String> lastName;
	public static volatile SingularAttribute<Professor, String> fullName;
	public static volatile SingularAttribute<Professor, String> number;
	public static volatile SingularAttribute<Professor, String> email;
	public static volatile SingularAttribute<Professor, String> password;
	public static volatile SingularAttribute<Professor, String> countryNic;
	public static volatile SingularAttribute<Professor, String> encryptedNic;
	public static volatile SingularAttribute<Professor, String> photo;
	public static volatile SingularAttribute<Professor, Instituition> instituition;
	public static volatile SetAttribute<Professor, Course> courses;
}
