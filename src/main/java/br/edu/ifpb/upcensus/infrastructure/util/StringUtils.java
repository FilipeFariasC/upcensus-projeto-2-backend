package br.edu.ifpb.upcensus.infrastructure.util;

public class StringUtils {
	private StringUtils() {}
	
    public static Boolean isEmpty(String str) {
        return ObjectUtils.isNull(str) || str.isEmpty();
    }
    public static Boolean notEmpty(String str) {
        return ObjectUtils.nonNull(str) && !str.isEmpty();
    }

    public static Boolean notBlank(String str) {
        return notEmpty(str) && str.chars()
        	.mapToObj(n -> (char) n)
        	.allMatch(n -> !Character.isWhitespace(n));
    }
}
