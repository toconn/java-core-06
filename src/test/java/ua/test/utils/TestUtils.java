package ua.test.utils;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import ua.core.data.NameValuePair;
import ua.core.utils.StringCollectionUtils;

public class TestUtils {
	
	public static <T> void testEquals (Collection<String> expected, Collection<String> actual) {
		
		assertEquals ("Sets are different sizes.", expected.size(), actual.size());
		
		for (String value : expected) {
			assertTrue ("Value '" + value + "' could not be found.", actual.contains (value));
		}
	}

	public static <T> void testEquals (List<String> expected, List<String> actual) {
		
		if (expected != null) {

			assertEquals ("Sets are different sizes. [" + StringCollectionUtils.asString (expected) + "] vs [" + StringCollectionUtils.asString (actual) + "]", expected.size(), actual.size());
			
			for (int i = 0; i < expected.size (); i++) {
				assertEquals ("List values do not match. " + expected.get(i) + " ≠ " + actual.get(i), expected.get(i), actual.get(i));
			}
		}
		else {
			
			assertNull ("Was expecting this to be null.", actual);
		}
	}

	public static <T> void testEquals (Map <String, T> expectedMap, Map<String, T> actualMap) {
		
		assertEquals ("Maps are different sizes.", expectedMap.size(), actualMap.size());
		
		for (String key : expectedMap.keySet()) {
			assertEquals ("Mismatch for key '" + key + "'.", expectedMap.get (key), actualMap.get (key));
		}
	}
	
	public static void testEquals (NameValuePair expected, NameValuePair actual) {
		
		assertEquals (expected.getName(), actual.getName());
		assertEquals (expected.getValue(), actual.getValue());
	}
}
