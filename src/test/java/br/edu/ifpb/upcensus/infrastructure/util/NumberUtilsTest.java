package br.edu.ifpb.upcensus.infrastructure.util;

import static br.edu.ifpb.upcensus.infrastructure.util.NumberUtils.isNumeric;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NumberUtilsTest {

	@Test
	void testIsNotNumber() {
		assertThat(isNumeric(".5")).isFalse();
		assertThat(isNumeric("+.5")).isFalse();
		assertThat(isNumeric("-.25")).isFalse();
		assertThat(isNumeric("1.")).isFalse();
		assertThat(isNumeric("-1.")).isFalse();
		assertThat(isNumeric("+1.")).isFalse();
		assertThat(isNumeric("-1.-2")).isFalse();
		assertThat(isNumeric("+1.-2")).isFalse();
		assertThat(isNumeric("+1.+2")).isFalse();
	}
	@Test 
	void testIsNumber() {
		assertThat(isNumeric("0.5")).isTrue();
		assertThat(isNumeric("-1.25")).isTrue();
		assertThat(isNumeric("-15.05")).isTrue();
		assertThat(isNumeric("+1.25")).isTrue();
		assertThat(isNumeric("+15.05")).isTrue();
	}

}
