package com.fcul.grouper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverterUtil {

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static Date extractDate(final String date) throws ParseException {
		return (date != null) ? formatter.parse(date) : null;
	}

}
