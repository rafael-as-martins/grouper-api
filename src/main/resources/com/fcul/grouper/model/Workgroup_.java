package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-19T10:59:48.784+0000")
@StaticMetamodel(Workgroup.class)
public class Workgroup_ {
	public static volatile SingularAttribute<Workgroup, Long> id;
	public static volatile SingularAttribute<Workgroup, Project> project;
	public static volatile SetAttribute<Workgroup, Student> students;
}
