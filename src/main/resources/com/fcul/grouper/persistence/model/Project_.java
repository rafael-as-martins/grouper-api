package com.fcul.grouper.persistence.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T13:47:48.273+0000")
@StaticMetamodel(Project.class)
public class Project_ {
	public static volatile SingularAttribute<Project, Long> id;
	public static volatile SingularAttribute<Project, String> name;
	public static volatile SingularAttribute<Project, Date> startDate;
	public static volatile SingularAttribute<Project, Date> endDate;
	public static volatile SingularAttribute<Project, Integer> maxElems;
	public static volatile SingularAttribute<Project, Boolean> classRestriction;
	public static volatile SingularAttribute<Project, Course> course;
	public static volatile SetAttribute<Project, Group> groups;
}
