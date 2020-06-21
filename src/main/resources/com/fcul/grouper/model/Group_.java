package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.297+0000")
@StaticMetamodel(Group.class)
public class Group_ {
	public static volatile SingularAttribute<Group, Long> id;
	public static volatile SingularAttribute<Group, Project> project;
	public static volatile SetAttribute<Group, Student> students;
}
