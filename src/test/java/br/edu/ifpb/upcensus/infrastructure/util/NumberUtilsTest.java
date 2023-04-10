package br.edu.ifpb.upcensus.infrastructure.util;

import static br.edu.ifpb.upcensus.infrastructure.util.NumberUtils.isNumber;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NumberUtilsTest {

	@Test
	void testIsNotNumber() {
		assertThat(isNumber(".5")).isFalse();
		assertThat(isNumber("+.5")).isFalse();
		assertThat(isNumber("-.25")).isFalse();
		assertThat(isNumber("1.")).isFalse();
		assertThat(isNumber("-1.")).isFalse();
		assertThat(isNumber("+1.")).isFalse();
		assertThat(isNumber("-1.-2")).isFalse();
		assertThat(isNumber("+1.-2")).isFalse();
		assertThat(isNumber("+1.+2")).isFalse();
	}
	@Test 
	void testIsNumber() {
		assertThat(isNumber("0.5")).isTrue();
		assertThat(isNumber("-1.25")).isTrue();
		assertThat(isNumber("-15.05")).isTrue();
		assertThat(isNumber("+1.25")).isTrue();
		assertThat(isNumber("+15.05")).isTrue();
	}

}
