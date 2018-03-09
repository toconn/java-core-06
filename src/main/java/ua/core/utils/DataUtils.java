package ua.core.utils;

import static ua.core.utils.NumberUtils.toStringCommaSeparatedDecimalOne;

public class DataUtils {
	
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
