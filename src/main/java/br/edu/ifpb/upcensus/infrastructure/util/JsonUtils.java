package br.edu.ifpb.upcensus.infrastructure.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonUtils {
	
	private JsonUtils() { }

	private static final char OBJECT_START = '{';
	private static final char OBJECT_END = '}';
	private static final char LIST_START= '[';
	private static final char LIST_END = ']';
	private static final char ELEMENT_DELIMITER = ',';
	private static final char STRING_OPEN_CLOSE = '"';
	private static final char KEY_VALUE_DELIMITER = ':';
	
	public static String stringListToJsonString(Collection<String> stringList) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(LIST_START);
		
		
		if (CollectionUtils.notEmpty(stringList)) {
			final String list = stringList
				.stream()
				.map(JsonUtils::build)
				.collect(Collectors.joining(","));
			builder.append(list);
		}
		
		builder.append(LIST_END);
		
		return builder.toString();
	}
	
	public static String mapToString(Map<?, ?> map) {
		return mapToString(map, Objects::toString);
	}
	
	public static <T> String mapToString(Map<T, ?> map, Function<T, String> keyMapper) {
		if (CollectionUtils.isEmpty(map)) return "null";
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append(OBJECT_START);
		
		final String keysValues = map.entrySet()
			.stream()
			.map((entry)->buildKeyValue(keyMapper.apply(entry.getKey()), Objects.toString(entry.getValue())))
			.collect(Collectors.joining(String.valueOf(ELEMENT_DELIMITER)));
		builder.append(keysValues);

		builder.append(OBJECT_END);
		
		return builder.toString();
	}
	
	private static String build(String str) {
		return STRING_OPEN_CLOSE + str + STRING_OPEN_CLOSE;
	}
	
	private static String buildKeyValue(String key, String value) {
		return build(key) + KEY_VALUE_DELIMITER + build(value);
	}
}
