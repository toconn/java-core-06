package ua.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordUtils {

	public static List<String> splitWord (String text) {
		
		List<String> words;
		
		if (StringUtils.isNotBlank (text)) {
			
			
			/*
			text = text.replaceAll(
				      String.format("%s|%s|%s",
				         "(?<=[A-Z])(?=[A-Z][a-z])",
				         "(?<=[^A-Z])(?=[A-Z])",
				         "(?<=[A-Za-z])(?=[^A-Za-z])"
				      ),
				      " ");
			*/

			
			// (?<=\S)\_(?=\S)
			// Handles
			//   - _ at the start, end stay.
			//   - _ in the middle replaced.
			
			// text = text.replace('_', ' ');
			
			text = text.replaceAll(
				      String.format("%s|%s|%s",
				         "(?<=[A-Z])(?=[A-Z][a-z])",
				         "(?<=[^A-Z])(?=[A-Z])",
				         "(?<=[A-Za-z0-9])(?=[^A-Za-z])"
				      ),
				      " ");
			
			words = Arrays.asList (text.split(" "));
		}
		else {
			words = new ArrayList<>();
		}
		
		return words;
	}
}
