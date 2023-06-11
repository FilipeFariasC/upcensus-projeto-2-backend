package br.edu.ifpb.upcensus.infrastructure.batch.reader.converter;

import java.util.Objects;

import com.github.miachm.sods.OfficeCurrency;
import com.github.miachm.sods.OfficePercentage;

import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

public class OdsColumnConverter {
	
	private static final String DEFAULT_FIELD_VALUE = StringUtils.EMPTY;
	
	private OdsColumnConverter() { }
	
	public static String convertColumn(PlainField expectedField, Object cellValue) {
		Type expectedType = expectedField.getType();
		if (expectedType.isInteger()) {
			return convertColumnToInteger(cellValue);
		}
		if (expectedType.isDecimal()) {
			return convertColumnToDecimal(cellValue);
		}
		return Objects.toString(cellValue, DEFAULT_FIELD_VALUE);
	}
	
	public static String convertColumnToInteger(Object cellValue) {
		if (cellValue instanceof Double)
			return Objects.toString(((Double) cellValue).longValue(), DEFAULT_FIELD_VALUE);
		if (cellValue instanceof Float)
			return Objects.toString(((Float) cellValue).longValue(), DEFAULT_FIELD_VALUE);
		if (cellValue instanceof OfficeCurrency)
			return Objects.toString(((OfficeCurrency) cellValue).getValue().longValue(), DEFAULT_FIELD_VALUE);
		if (cellValue instanceof OfficePercentage)
			return Objects.toString(((OfficePercentage) cellValue).getValue().longValue() * 100, DEFAULT_FIELD_VALUE);
			
		return Objects.toString(cellValue, DEFAULT_FIELD_VALUE);
	}

	
	public static String convertColumnToDecimal(Object cellValue) {
		if (cellValue instanceof Long)
			return Objects.toString(((Long) cellValue).doubleValue(), DEFAULT_FIELD_VALUE);
		if (cellValue instanceof Integer)
			return Objects.toString(((Integer) cellValue).doubleValue(), DEFAULT_FIELD_VALUE);
		if (cellValue instanceof OfficeCurrency)
			return Objects.toString(((OfficeCurrency) cellValue).getValue(), DEFAULT_FIELD_VALUE);
		if (cellValue instanceof OfficePercentage)
			return Objects.toString(((OfficePercentage) cellValue).getValue() * 100, DEFAULT_FIELD_VALUE);
			
		return Objects.toString(cellValue, DEFAULT_FIELD_VALUE);
	}
}
