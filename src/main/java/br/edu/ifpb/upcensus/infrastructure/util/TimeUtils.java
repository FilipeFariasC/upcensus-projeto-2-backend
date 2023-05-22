package br.edu.ifpb.upcensus.infrastructure.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtils {
	
	private static final TimeUtils instance = new TimeUtils();
	
	private TimeUtils() {}
	public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
	
	public static final String FILE_TIMESTAMP = "yyyy-MM-dd_HH-mm-ss-SSSSSS";
	
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS";
	public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
	public static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(FILE_TIMESTAMP);
	
	public static String toString(LocalDate localDate, DateTimeFormatter formatter) {
		return localDate.format(formatter);
	}
	public static String toString(LocalDate localDate) {
		return toString(localDate, DEFAULT_FORMATTER);
	}
	public static String toString(LocalDateTime localDateTime, String format) {
		return toString(localDateTime, DateTimeFormatter.ofPattern(format));
	}
	public static String toString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
		return localDateTime.format(formatter);
	}
	public static String toString(LocalDateTime localDateTime) {
		return toString(localDateTime, DEFAULT_FORMATTER);
	}
	public static LocalDateTime toLocalDateTime(Date dateToConvert) {
	    return dateToConvert
	    	.toInstant()
	    	.atZone(ZoneId.systemDefault())
	    	.toLocalDateTime();
	}
	
	public static String durationToString(Duration duration) {
	    return duration.toString()
	            .substring(2)
	            .replaceAll("(\\d[HMS])(?!$)", "$1 ")
	            .toLowerCase();
	}
	
	public static boolean isValidDate(String date) {
		try {
			LocalDate.parse(date);
		} catch(RuntimeException exception) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidTimestamp(String timestamp) {
		try {
			LocalDateTime.parse(timestamp);
		} catch(RuntimeException exception) {
			return false;
		}
		return true;
	}
	
	public static TimeUtils getInstance() {
		return instance;
	}
}
