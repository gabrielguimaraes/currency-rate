package br.com.gabrielguimaraes.currencyrate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	public final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public static LocalDateTime parseStringLocalDateTime(String stringLocalDate) {
		return LocalDateTime.parse(stringLocalDate, DATE_TIME_FORMATTER);
	}
	
	
	public static Date parseStringDate(String stringLocalDate) throws ParseException {
		Date date = new Date();
		return DATE_FORMATTER.parse(stringLocalDate);
	}
	
}
