package ua.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringCollectionUtils {
	
	private static final String[] EMPTY_ARRAY = new String[0];
	
	public static List<String> append (List<String> texts, String append) {
		
		List<String> appendedTexts = new ArrayList<>();
		
		for (String text : texts) {
			appendedTexts.add (text + append);
		}
		
		return texts;
	}

	public static String[] asArray (String text) {
		
		String lines[];
		
		if (StringUtils.isNotEmpty (text)) {
			lines = text.split("\\r?\\n");
		}
		else {
			lines = EMPTY_ARRAY;
		}
		
		return lines;
	}
	
	public static String[] asArray (List<String> texts) {
		
		return (String[]) texts.toArray();
	}
	
	public static List<String> asList (String text) {
		
		return asList (asArray (text));
	}
	
	public static List<String> asList (String[] texts) {
		
		return Arrays.asList (texts);
	}
	
	/**
	 * Turns a string set into a map <string, string> where the name and the value are the same.
	 */
	public static Map <String, String> asMap (Set <String> stringSet) {
		
		Map <String, String> stringMap = new HashMap <String, String>();
		
		for (String item: stringSet) {
			stringMap.put (item, item);
		}
		
		return stringMap;
	}

	/**
	 * Turns a string set into a map <string, string> where the name and the value are the same.
	 */
	public static Map <String, String> asMapIgnoreCase (Collection <String> strings) {
		
		Map <String, String> stringMap = new MapIgnoreCase <String>();
		
		for (String item: strings) {
			stringMap.put (item, item);
		}
		
		return stringMap;
	}
	
	public static String asString (List<String> texts) {
		
		return join (texts, "\n");
	}
	
	/**
	 * Merges the contents of a list together into one string.
	 * Each element in the string is separated by the separator string.
	 * 
	 * @param stringList
	 * @param separator
	 * @return
	 */
	public static String join (List<String> stringList) {
		
		// Declarations:
		
		StringBuffer	stringBuffer;
		
		// Main:
		
		stringBuffer	= new StringBuffer();
		
		for (String string : stringList) {
			stringBuffer.append (string);
		}
		
		return stringBuffer.toString();
	}

	/**
	 * Merges the contents of a list together into one string.
	 * Each element in the string is separated by the separator string.
	 * 
	 * @param stringList
	 * @param separator
	 * @return
	 */
	public static String join (List<String> stringList, String separator) {
		
		// Declarations:
		
		StringBuffer	stringBuffer;
		boolean			isFirst;
		
		// Main:
		
		stringBuffer	= new StringBuffer();
		isFirst			= true;
		
		for (String string : stringList) {
			
			if (isFirst) {
				isFirst = false;
			}
			else {
				stringBuffer.append (separator);
			}
			
			stringBuffer.append (string);
		}
		
		return stringBuffer.toString();
	}
	
	public static String[] removeBlanks (String[] texts) {
		
		List<String> results;
		
		results = removeBlanks (CollectionUtils.asList (texts));
		return asArray (results);
	}
	
	public static List<String> removeBlanks (List<String> texts) {
		
		List<String> results = new ArrayList<>();
		
		for (String text : texts) {
			if (StringUtils.isNotBlank (text)) {
				results.add (text);
			}
		}
		
		return results;
	}
	
	public static List<String> removeStartsWith (String[] texts, String start) {
		
		List<String> results = new ArrayList<>();
		
		for (String text : texts) {
			if (StringUtils.isNotStartsWith (text, start)) {
				results.add (text);
			}
		}
		
		return results;
	}
	
	public static List<String> removeStartsWith (List<String> texts, String start) {
		
		List<String> results = new ArrayList<>();
		
		for (String text : texts) {
			if (StringUtils.isNotStartsWith (text, start)) {
				results.add (text);
			}
		}
		
		return results;
	}
	
	public static List<String> removeStartsWithIgnoreCase (String[] texts, String start) {
		
		List<String> results = new ArrayList<>();
		
		for (String text : texts) {
			if (StringUtils.isNotStartsWithIgnoreCase (text, start)) {
				results.add (text);
			}
		}
		
		return results;
	}
	
	public static List<String> removeStartsWithIgnoreCase (List<String> texts, String start) {
		
		List<String> results = new ArrayList<>();
		
		for (String text : texts) {
			if (StringUtils.isNotStartsWithIgnoreCase (text, start)) {
				results.add (text);
			}
		}
		
		return results;
	}
	
	public static List<String> trim (List<String> texts) {
		
		List<String> results = new ArrayList<>();
		
		for (String text : texts) {
			results.add (StringUtils.trim (text));
		}
		
		return results;
	}
}