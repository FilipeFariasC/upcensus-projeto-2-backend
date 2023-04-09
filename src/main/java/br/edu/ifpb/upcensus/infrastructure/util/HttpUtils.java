package br.edu.ifpb.upcensus.infrastructure.util;

import java.util.Arrays;

import org.springframework.http.HttpMethod;

public class HttpUtils {
	private HttpUtils() {}
	
	public static String[] allHttpMethods() {
		return Arrays.stream(HttpMethod.values())
			.map(Enum::name)
			.toArray(String[]::new);
	}
}
