package com.fcul.grouper.utils;

import com.fcul.grouper.model.types.search.ClassSearchFieldsMapper;
import com.fcul.grouper.model.types.search.StudentSearchFieldsMapper;

public class DBFieldMapper {

	public static String getMappedField(final String entityAlias, final String entityFieldSeparator,
			final String... fields) {

		StringBuilder sb = new StringBuilder();

		int fieldsNumber = fields.length;
		for (String field : fields) {

			fieldsNumber--;
			sb.append(entityAlias).append(entityFieldSeparator).append(field);

			if (fieldsNumber != 0) {
				sb.append(" ").append("AND").append(" ");
			}

		}

		return sb.toString();
	}

	public static String getLikeMappedField(final String field) {
		return new StringBuilder().append("%").append(field).append("%").toString();
	}

	public static String getOrderField(String orderField) {

		try {
			return StudentSearchFieldsMapper.valueOf(orderField).getBDMappedField();
		} catch (IllegalArgumentException e) {
			return ClassSearchFieldsMapper.valueOf(orderField).getBDMappedField();
		}

	}

}
