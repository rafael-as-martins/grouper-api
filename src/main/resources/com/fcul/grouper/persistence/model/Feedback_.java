package com.fcul.grouper.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T13:47:48.254+0000")
@StaticMetamodel(Feedback.class)
public class Feedback_ {
	public static volatile SingularAttribute<Feedback, Long> id;
	public static volatile SingularAttribute<Feedback, String> type;
	public static volatile SingularAttribute<Feedback, Double> grade;
	public static volatile SingularAttribute<Feedback, String> content;
	public static volatile SingularAttribute<Feedback, Group> group;
	public static volatile SingularAttribute<Feedback, Professor> professor;
	public static volatile SingularAttribute<Feedback, Course> course;
}
