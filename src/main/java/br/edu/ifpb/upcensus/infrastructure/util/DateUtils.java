package br.edu.ifpb.upcensus.infrastructure.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	
	private DateUtils() {}
	
	public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSSSSS";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSSSSS");
	
	public static String toString(LocalDate localDate) {
		return localDate.format(formatter);
	}
	public static String toString(LocalDateTime localDateTime) {
		return localDateTime.format(formatter);
	}
	public static LocalDateTime toLocalDateTime(Date dateToConvert) {
	    return dateToConvert
	    	.toInstant()
	    	.atZone(ZoneId.systemDefault())
	    	.toLocalDateTime();
	}
}
