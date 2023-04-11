package br.edu.ifpb.upcensus.infrastructure.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.regex.Pattern;

public class NumberUtils {
	private NumberUtils() {}

	private static final Pattern NUMERIC_PATTERN = Pattern.compile("^[-+]?[0-9]+([.,][0-9]+)?$");
	private static final Pattern POSITIVE_NUMERIC_PATTERN = Pattern.compile("^+?[0-9]+([.,][0-9]+)?$");
	private static final Pattern DECIMAL_PATTERN = Pattern.compile("^[-+]?[0-9]+$");
	private static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("^+?[0-9]+$");
	
	public static boolean isNumeric(String str) {
		if (StringUtils.isEmpty(str)) return false;
		
		return NUMERIC_PATTERN.matcher(str).matches();
	}
	public static boolean isPositiveNumeric(String str) {
		if (StringUtils.isEmpty(str)) return false;
		
		return POSITIVE_NUMERIC_PATTERN.matcher(str).matches();
	}
	public static boolean isDecimal(String str) {
		if (StringUtils.isEmpty(str)) return false;
		
		return DECIMAL_PATTERN.matcher(str).matches();
	}
	public static boolean isPositiveDecimal(String str) {
		if (StringUtils.isEmpty(str)) return false;
		
		return POSITIVE_INTEGER_PATTERN.matcher(str).matches();
	}
	
    public static <R extends Number> R convertToNumber(String num, Function<String, R> converter) {
        return converter.apply(num);
    }
    public static Integer convertToInteger(String num) {
        return convertToNumber(num, Integer::valueOf);
    }
    public static Long convertToLong(String num) {
        return convertToNumber(num, Long::valueOf);
    }
    public static Double convertToDouble(String num) {
        return convertToNumber(num, Double::valueOf);
    }
    public static Float convertToFloat(String num) {
        return convertToNumber(num, Float::valueOf);
    }
    public static BigDecimal convertToBigDecimal(String num) {
        return convertToNumber(num, BigDecimal::new);
    }
    public static BigInteger convertToBigInteger(String num) {
        return convertToNumber(num, BigInteger::new);
    }

}
