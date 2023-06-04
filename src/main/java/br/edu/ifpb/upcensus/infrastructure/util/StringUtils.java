package br.edu.ifpb.upcensus.infrastructure.util;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	private static StringUtils instance = new StringUtils();
	private StringUtils() {}
	
    public static Boolean isEmpty(String str) {
        return ObjectUtils.isNull(str) || str.isEmpty();
    }
    public static Boolean notEmpty(String str) {
        return ObjectUtils.nonNull(str) && !str.isEmpty();
    }

    public static Boolean notBlank(String str) {
        return notEmpty(str) && str.trim().chars()
        	.mapToObj(n -> (char) n)
        	.anyMatch(n -> !Character.isWhitespace(n));
    }
    
    public static String line() {
    	return System.lineSeparator();
    }
    
    public static StringUtils getInstance() {
    	return instance;
    }
}
