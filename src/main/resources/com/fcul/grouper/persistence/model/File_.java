package com.fcul.grouper.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T13:47:48.256+0000")
@StaticMetamodel(File.class)
public class File_ {
	public static volatile SingularAttribute<File, Long> id;
	public static volatile SingularAttribute<File, String> url;
	public static volatile SingularAttribute<File, Student> student;
	public static volatile SingularAttribute<File, Group> group;
}
