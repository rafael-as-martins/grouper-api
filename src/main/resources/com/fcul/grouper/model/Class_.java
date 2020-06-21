package com.fcul.grouper.model;

import com.fcul.grouper.persistence.model.keys.StudentCourseKey;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.290+0000")
@StaticMetamodel(Class.class)
public class Class_ {
	public static volatile SingularAttribute<Class, StudentCourseKey> id;
	public static volatile SingularAttribute<Class, Student> student;
	public static volatile SingularAttribute<Class, Course> course;
	public static volatile SingularAttribute<Class, String> name;
}
