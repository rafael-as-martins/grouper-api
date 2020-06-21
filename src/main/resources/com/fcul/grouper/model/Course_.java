package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.293+0000")
@StaticMetamodel(Course.class)
public class Course_ {
	public static volatile SingularAttribute<Course, Long> id;
	public static volatile SingularAttribute<Course, String> name;
	public static volatile SetAttribute<Course, Professor> professors;
}
