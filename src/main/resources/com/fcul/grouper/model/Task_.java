package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.307+0000")
@StaticMetamodel(Task.class)
public class Task_ {
	public static volatile SingularAttribute<Task, Long> id;
	public static volatile SingularAttribute<Task, String> content;
	public static volatile SingularAttribute<Task, Boolean> state;
	public static volatile SingularAttribute<Task, Group> group;
}
