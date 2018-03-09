package ua.core.utils.console;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ua.core.utils.CollectionUtils;
import ua.core.utils.DataUtils;
import ua.core.utils.Date2Utils;
import ua.core.utils.DateUtils;
import ua.core.utils.StringUtils;

public class ConsoleUtils {

	public static final int 		INDENT_SPACE_COUNT			= 4;

	private static final int		DEFAULT_COLUMN_1_WIDTH		= 24;
	private static final String	DEFAULT_COLUMN_SEPARATER		= ": ";
	private static final String	BORDER_CHARACTER				= "*";

	private static int			column1Width					= DEFAULT_COLUMN_1_WIDTH;
	private static String		columnSeparater				= DEFAULT_COLUMN_SEPARATER;
	
	public static int getColumn1Width() {
		return column1Width;
	}
	
	public static String getColumnSeparater() {
		return columnSeparater;
	}
	
	public static void print () {
		print ("");
	}
	
	public static void print (Throwable e) {
		print (e, null);
	}
	
	public static void print (Throwable e, String description) {

		print ("Exception",		e.getClass().getName());
		
		if (description != null) {
			print ("   Description", description);
		}
		
		print ("   Message",	e.getMessage () != null ? e.getMessage() : "[null]");		
		print ();
		
		print ("   Stack Trace");
		e.printStackTrace();
		
		print ();
	}

	public static void print (Throwable e, String className, String methodName, String comment) {

		print ("Exception",		e.getClass ().getName ());
		print ("   Message",	e.getMessage ());
		print ("   Class",		className);
		print ("   Method",		methodName);

		if (comment != null) {
			
			print ("   comment", comment);
		}

		print ("");
	}

	public static void print (Object obj) {
		System.out.println (obj);
	}
	
	public static void print (Collection <String> texts) {
		print (StringUtils.join (texts, ", "));
	}

	public static void print (String text) {
		System.out.println (text);
	}

	public static void print (String text1, boolean boolValue) {
		print (text1, Boolean.toString (boolValue));
	}
	
	public static void print (String text1, Collection <String> texts) {
		print (text1, StringUtils.join (texts, ", "));
	}

	public static void print (String text1, Date date) {
		print (text1, DateUtils.toTimestampString (date));
	}

	public static void print (String text1, double number) {
		print (text1, Double.toString (number));
	}

	public static void print (String text1, Throwable e) {
		print (e, text1);
	}

	public static void print (String text1, float number) {
		print (text1, Float.toString (number));
	}

	public static void print (String text1, int number) {
		print (text1, Integer.toString (number));
	}

	public static void print (String text1, LocalDate date) {
		print (text1, Date2Utils.toTimestampString (date));
	}

	public static void print (String text1, LocalDateTime dateTime) {
		print (text1, Date2Utils.toTimestampString (dateTime));
	}

	public static void print (String text1, LocalTime time) {
		print (text1, Date2Utils.toTimestampString (time));
	}

	public static void print (String text1, long number) {
		print (text1, Long.toString (number));
	}
	
	public static void print (String text, Object value) {
		print (text, value.toString());
	}

	public static void print (String text1, String text2) {
		print (padString (text1, " ", getColumn1Width()) + getColumnSeparater() + text2);
	}
	
	public static void print (String className, String methodName, String comment, Throwable e) {
		print (e, className, methodName, comment);
	}
	
	public static void printDoesntWork() {
		
		print ("*****************************************************************************");
		print ("**  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  **");
		print ("**  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  **");
		print ("**  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  **");
		print ("**  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  Doesn't Work  -  **");
		print ("*****************************************************************************");
	}
	
	public static void printDone() {
		print ();
		print ("Done.");
		print ();
	}

	public static void printDone (String title) {
		
		int length = title.length();
		int halfLength = (length - 5) / 2;

		print ();
		print (StringUtils.expand (BORDER_CHARACTER, halfLength + 4) + "  Done.  " + StringUtils.expand (BORDER_CHARACTER, halfLength + 5 - length % 2));
		print ();
	}

	public static void printDouble () {
		print ();
		print ();
	}

	public static void printDouble (Throwable e) {
		print (e);		
		print ();
	}
	
	public static void printDouble (Throwable e, String description) {
		print (e, description);	
		print ();
	}

	public static void printDouble (Throwable e, String className, String methodName, String comment) {
		print (e, className, methodName, comment);
		print ();
	}

	public static void printDouble (Collection <String> texts) {
		print (texts);
		print ();
	}

	public static void printDouble (String text) {
		print (text);
		print ();
	}

	public static void printDouble (String text1, boolean boolValue) {
		print (text1, boolValue);
		print ();
	}
	
	public static void printDouble (String text1, Collection <String> texts) {
		print (text1, texts);
		print ();
	}

	public static void printDouble (String text1, Date date) {
		print (text1, date);
		print ();
	}
	
	public static void printDouble (String text1, Double number) {
		print (text1, number);
		print ();
	}
	
	public static void printDouble (String text1, Throwable e) {
		print (e, text1);
		print ();
	}

	public static void printDouble (String text1, float number) {
		print (text1, number);
		print ();
	}
	
	public static void printDouble (String text1, int number) {
		print (text1, number);
		print ();
	}
	
	public static void printDouble (String text1, LocalDate date) {
		print (text1, date);
		print ();
	}
	
	public static void printDouble (String text1, LocalDateTime dateTime) {
		print (text1, dateTime);
		print ();
	}
	
	public static void printDouble (String text1, LocalTime time) {
		print (text1, time);
		print ();
	}
	
	public static void printDouble (String text1, long number) {
		print (text1, number);
		print ();
	}
	
	public static void printDouble (String text1, String text2) {
		print (text1, text2);
		print ();
	}
	
	public static void printDouble (String className, String methodName, String comment, Throwable e) {
		print (e, className, methodName, comment);
		print ();
	}

	public static void printDoubleSorted (String text1, Collection <String> texts) {
		printSorted (text1, texts);
		print ();
	}
	
	public static void printHasIssues() {
		
		print ("***********************************************************************************");
		print ("**  -  Has Issues  -  Has Issues  -  Has Issues  -  Has Issues  - Has Issues  -  **");
		print ("**  -  Has Issues  -  Has Issues  -  Has Issues  -  Has Issues  - Has Issues  -  **");
		print ("**  -  Has Issues  -  Has Issues  -  Has Issues  -  Has Issues  - Has Issues  -  **");
		print ("**  -  Has Issues  -  Has Issues  -  Has Issues  -  Has Issues  - Has Issues  -  **");
		print ("***********************************************************************************");
	}
	
	public static void printHeader (String headerText) {
		printSectionHeader (headerText);
	}

	
	public static void printIndent (int indentCount, Object value) {
		print (indent (indentCount, value.toString()));
	}

	public static void printIndent (int indentCount, String text) {
		print (indent (indentCount, text));
	}

	public static void printIndent (int indentCount, String text, Object value) {
		print (indent (indentCount, text), value);
	}

	public static void printIndent (int indentCount, String text1, String text2) {
		print (indent (indentCount, text1), text2);
	}

	public static void printInline (String text) {
		System.out.print (text);
	}
	
	public static void printObject (int indent, Map<String, Object> map) {
		printObject (indent, "", map);
	}
	
	public static void printObject (int indent, Collection<Object> collection) {
		printObject (indent, "", collection);
	}
	
	public static void printObject (int indent, Object value) {
		printObject (indent, "", value);
	}
	
	public static void printObject (int indent, String lead, Collection<Object> collection) {
		
		printIndent (indent, lead + "Collection", formattedClassName (collection));
		
		indent++;
		
		for (Object value : collection) {
			
			if (value == null) {
				printIndent (indent, "[null]");
			}
			else if (DataUtils.isValueObject (value)) {
				printIndent (indent, value.toString(), formattedClassName (value));
			}
			else {
				printObject (indent, value);
			}

		}
	}

	public static void printObject (int indent, String lead, Map<String, Object> map) {
		
		Object value;
		
		printIndent (indent, lead + "Map", formattedClassName (map));
		
		indent++;
		
		for (String key : map.keySet()) {
			
			value = map.get (key);
			
			if (value == null) {
				printIndent (indent, key + " = " + "[null]");
			}
			else if (DataUtils.isValueObject (value)) {
				printIndent (indent, key + " = " + value.toString(), formattedClassName (value));
			}
			else {
				printObject (indent, key + " = ", value);
			}

		}
	}

	@SuppressWarnings ({ "unchecked"})
	public static void printObject (int indent, String lead, Object value) {
				
		indent++;
		
		if (value == null) {
			printIndent (indent, "[null]");
		}
		if (value instanceof Map) {
			printObject (indent, lead, (Map<String, Object>) value);
		}
		else if (value instanceof Collection) {
			printObject (indent, lead, (Collection<Object>) value);
		}
		else {
			printIndent (indent, lead + value.toString());
		}

	}
	
	public static void printSectionHeader (String headerText) {
		
		print ();
		print ("************  " + headerText + "  ************");
		print ();
	}
	
	public static void printSorted (String text1, Collection <String> texts) {
		
		print (text1, StringUtils.join (CollectionUtils.asSortedList (texts), ", "));
	}
	
	public static void printTitle (String title) {
		
		int length = title.length();
		
		print (StringUtils.expand (BORDER_CHARACTER, length + 12));
		print (BORDER_CHARACTER + BORDER_CHARACTER + "    " + title + "    " + BORDER_CHARACTER + BORDER_CHARACTER);
		print (StringUtils.expand (BORDER_CHARACTER, length + 12));
		print ();
	}
	
	public static void printXml (String xmlString) {
		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

        Source				xmlInput				= null;
        StringWriter			stringWriter			= null;
        StreamResult			xmlOutput			= null;
        
        TransformerFactory	transformerFactory	= null;
        Transformer			transformer			= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

        try {
        	
	        xmlInput		= new StreamSource (new StringReader (xmlString));
	        stringWriter	= new StringWriter();
	        xmlOutput		= new StreamResult (stringWriter);
	        
	        transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute ("indent-number", 4);
	        
	        transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty (OutputKeys.INDENT, "yes");
	        transformer.transform (xmlInput, xmlOutput);
	        
	        print (xmlOutput.getWriter().toString());
	        
	    }
	    catch (Exception e) {

	    	print (e);
	    }
	}

	public static void resetColumn1Width() {
		ConsoleUtils.column1Width = DEFAULT_COLUMN_1_WIDTH;
	}
	
	public static void resetColumnSeparater() {
		ConsoleUtils.columnSeparater = DEFAULT_COLUMN_SEPARATER;
	}
	
	public static void setColumn1Width (int column1Width) {
		ConsoleUtils.column1Width = column1Width;
	}
	
	public static void setColumnSeparater (String columnSeparater) {
		ConsoleUtils.columnSeparater = columnSeparater;
	}
	
	public static void sleep (int seepTimeMs) {
		
		try {
			Thread.sleep (seepTimeMs);
		}
		catch (InterruptedException e) {
			// Ignore.
		}
	}
	
	public static void sleepSecs (int sleepTimeSecs) {
		
		sleep (sleepTimeSecs * 1000);
	}	
	
	/**
	 * Expands a string by a given number of times.
	 * 
	 * Creation date: (2002/04/11 4:54:09 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param expandString String
	 * @param expandCount int
	 */
	private static String expandString (String expandString, int expandCount) {
	
		///////////////////////////////////////////
		// Declarations -
		///////////////////////////////////////////
	
		StringBuffer	expandedStringBuffer = null;
		int				i;
	
	
		///////////////////////////////////////////
		// Code -
		///////////////////////////////////////////
	
		if (expandString != null && expandString.length() > 0 && expandCount > 0) {
	
			// Create buffer to final string size.
	
			expandedStringBuffer = new StringBuffer (expandString.length() * expandCount);
	
	
			// Build string.
			
			for (i = 0; i < expandCount; i++) {
	
				expandedStringBuffer.append (expandString);
			}
	
			return expandedStringBuffer.toString();
	
		}
		else {
			
			return "";
		}
	}
	
	public static String indent (int indentCount) {
		return StringUtils.expand (' ', indentCount * INDENT_SPACE_COUNT);
	}

	
	private static String indent (int indentCount, String text) {
		
		String indent;
			
		indent = indent (indentCount);
			
		if (StringUtils.isNotEmpty (text)) {
				
			// Indent at every newline:
			text = text.replaceAll ("[\n]", "\n" + indent);
			text = indent + text;
		}
		
		return text;
	}

	private static String padString (String text, String fillText, int totalLength) {
		
		if (text != null) {
		
			return text + expandString (fillText, totalLength - text.length());
		}
		else {
			
			return expandString (fillText, totalLength);
		}
	}
	
	private static String formattedClassName (Object value) {
		return  "[" + value.getClass().getSimpleName() +"]";
	}
}
