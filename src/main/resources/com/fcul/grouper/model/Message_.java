package com.fcul.grouper.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-14T18:51:20.299+0000")
@StaticMetamodel(Message.class)
public class Message_ {
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, String> content;
	public static volatile SetAttribute<Message, Message> messages;
}
