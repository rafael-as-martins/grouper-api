package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.304+0000")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> firstName;
	public static volatile SingularAttribute<Student, String> lastName;
	public static volatile SingularAttribute<Student, String> lastfullName;
	public static volatile SingularAttribute<Student, String> degree;
	public static volatile SingularAttribute<Student, String> number;
	public static volatile SingularAttribute<Student, String> email;
	public static volatile SingularAttribute<Student, String> password;
	public static volatile SingularAttribute<Student, String> countryNic;
	public static volatile SingularAttribute<Student, String> photo;
	public static volatile SingularAttribute<Student, Instituition> instituition;
	public static volatile SetAttribute<Student, Group> groups;
}
