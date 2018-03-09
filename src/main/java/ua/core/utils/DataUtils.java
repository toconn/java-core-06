package ua.core.utils;

import static ua.core.utils.NumberUtils.toStringCommaSeparatedDecimalOne;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DataUtils {
	
	public static boolean isPrimitiveClass (Object value) {
		
		if (value instanceof Boolean) return true;
		if (value instanceof Byte) return true;
		if (value instanceof Double) return true;
		if (value instanceof Character) return true;
		if (value instanceof Float) return true;
		if (value instanceof Integer) return true;
		if (value instanceof Long) return true;
		if (value instanceof Short) return true;
		
		return false;
	}
	
	public static boolean isValueObject (Object value) {
		
		if (value instanceof String) return true;
		
		if (isPrimitiveClass (value)) return true;
		
		if (value instanceof BigDecimal) return true;
		if (value instanceof Date) return true;
		if (value instanceof Instant) return true;
		if (value instanceof LocalDate) return true;
		if (value instanceof LocalDateTime) return true;
		
		return false;
	}
	
	public static String formatMemoryGigabytesDecimalOne (long value) {
		return toStringCommaSeparatedDecimalOne (value / 1073741824.0f) + " gb";
	}

	public static String formatMemoryKilobytesDecimalOne (long value) {
		return toStringCommaSeparatedDecimalOne (value / 1024.0f) + " kb";
	}

	public static String formatMemoryMegabytesDecimalOne (long value) {
		return toStringCommaSeparatedDecimalOne (value / 1048576.0f) + " mb";
	}

}
