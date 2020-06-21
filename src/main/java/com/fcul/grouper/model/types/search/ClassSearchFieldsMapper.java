package com.fcul.grouper.model.types.search;

import com.fcul.grouper.utils.DBFieldMapper;

public enum ClassSearchFieldsMapper {

	CLASS("id.name");

	private String BDMappedField;

	public static final String ENTITY_ALIAS = "c";

	public static final String ALIAS_FIELD_SEPARATOR = ".";

	private ClassSearchFieldsMapper(String... fields) {
		this.BDMappedField = DBFieldMapper.getMappedField(ENTITY_ALIAS, ALIAS_FIELD_SEPARATOR, fields);

	}

	public String getBDMappedField() {
		return BDMappedField;
	}

	public void setBDMappedField(String bDMappedField) {
		BDMappedField = bDMappedField;
	}

}
