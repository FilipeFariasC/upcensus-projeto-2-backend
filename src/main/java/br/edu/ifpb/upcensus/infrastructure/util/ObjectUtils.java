package br.edu.ifpb.upcensus.infrastructure.util;

import java.util.Objects;

public class ObjectUtils {
	private ObjectUtils() {}
	
    public static Boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
    public static Boolean nonNull(Object obj) {
        return Objects.nonNull(obj);
    }
}
