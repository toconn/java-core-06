package ua.core.utils;

public class ValidateUtils {

	public static void isNull (Object item, String name) {
		
		if (item != null) {
            throw new NullPointerException (name + " - must be null.");
        }
	}

	public static void notNull (Object item, String name) {
		
		if (item == null) {
            throw new NullPointerException (name + " - null not allowed.");
        }
	}
}