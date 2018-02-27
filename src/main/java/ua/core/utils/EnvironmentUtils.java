package ua.core.utils;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentUtils {

	/*
	 * Returns the variable in a form EnvironmentService can parse in a string.
	 * Can be used in .evaluate() methods.
	 * 
	 */
	public static String asParsible (String variableName) {
		return "%" + variableName + "%";
	}
	
	/**
	 * Returns a list of strings with the string environment variables expanded.
	 * 
	 * Null safe (returns empty list).
	 * 
	 * @param textList
	 * @return
	 */
	public static List <String> evaluate (List<String> textList) {
		
		List<String>	list;
		
		list = new ArrayList <String>();
		
		if (CollectionUtils.isNotEmpty (textList)) {
			
			for (String text : textList) {
				list.add (EnvironmentUtils.evaluate (text));
			}
		}
		
		return list;
	}
	
    /** Evaluates all the environment variables (%var%) in a string and replaces them
     * with their environment values (if they exist). 
     * 
     * @param envString
     */
    public static String evaluate (String envString) {
    	
    	String			envName					= null;
    	String			envValue				= null;
    	
		StringBuilder	expandedStringBuilder	= null;
		
		int				previousIndex			= 0;
		int				nextIndex				= 0;
		
		boolean			startingPercentFound	= false;


		if (StringUtils.isNotEmpty (envString)) {
			
			expandedStringBuilder = new StringBuilder();
			
			while (nextIndex > -1 && previousIndex < envString.length()) {
				
		    	// find next %
				
				nextIndex = envString.indexOf ('%', previousIndex);
				
				if (nextIndex > -1) {
		    	
					if (! startingPercentFound) {
						
						// if opening %...
		    	
						// append previous text to return string
						
						expandedStringBuilder.append (envString.substring (previousIndex, nextIndex));
						
						
		    			// update previous index...
					
						previousIndex = nextIndex + 1;
						startingPercentFound = true;
						
					}
					else {
		    		
						// if closing %...
						
						// lookup environment variable...
						
						if (previousIndex < nextIndex - 1) {
						
							envName  = envString.substring (previousIndex, nextIndex);
							envValue = System.getenv (envName);
						}
						else {
							
							envValue = null;
						}
		    	
		    			if (StringUtils.isNotEmpty (envValue)) {
		    				
		    				// Append environment value...
		    				
		    				expandedStringBuilder.append (envValue);
		    			}
		    			else {
		    				
		    				// Has no value. Append as it was in original string...
		    				
		    				expandedStringBuilder.append (envString.substring (previousIndex -1, nextIndex + 1));
		    			}

		    			
		    			// update previous index...
						
						previousIndex = nextIndex + 1;
						startingPercentFound = false;
					}
				}

			}
			
			if (previousIndex < envString.length()) {
				
				// Append remaining text
				expandedStringBuilder.append (envString.substring (previousIndex));
			}
	    	
	    	// return string
				
			return expandedStringBuilder.toString();
			
		}
	    else {
	    	
	    	return null;
	    }
    }

	/**
	 * Return the environment value for the variable.
	 * 
	 * @param envName
	 * @return
	 */
	public static String getValue(String envName) {
		return System.getenv (envName);
	}
	
	public static boolean isExists (String envName) {
		return  ! isNotExists (envName);
	}

	public static boolean isNotExists (String envName) {
		return StringUtils.isNotEmpty (envName) && StringUtils.isEmpty (getValue (envName));
	}
	
	public static void printEnvValues() {

		List <String> sortedKeyList;
		sortedKeyList = CollectionUtils.sort (System.getenv().keySet ());
		
		for (String key: sortedKeyList) {
			System.out.println (StringUtils.pad (key, 24) + ": " + System.getProperty (key));
		}
	}
}
