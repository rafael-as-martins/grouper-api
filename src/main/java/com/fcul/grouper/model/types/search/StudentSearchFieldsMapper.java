package com.fcul.grouper.model.types.search;

import com.fcul.grouper.utils.DBFieldMapper;

public enum StudentSearchFieldsMapper {

	FIRSTNAME("firstName"),

	LASTNAME("lastName"),

	STUDENT_NUMBER("number"),

	EMAIL("email");

	private String BDMappedField;

	public static final String ENTITY_ALIAS = "s";

	public static final String ALIAS_FIELD_SEPARATOR = ".";

	private StudentSearchFieldsMapper(String... fields) {
		this.BDMappedField = DBFieldMapper.getMappedField(ENTITY_ALIAS, ALIAS_FIELD_SEPARATOR, fields);

	}

	public String getBDMappedField() {
		return BDMappedField;
	}

	public void setBDMappedField(String bDMappedField) {
		BDMappedField = bDMappedField;
	}

}
