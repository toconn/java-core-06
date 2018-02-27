package ua.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.core.data.NameValuePair;

/**
 * Parses a string into a list of strings.
 * The parser will parse on a list of separators.
 * Escape characters can be included. They will be converted from the escape format into the target format.
 * ... NameValuePair (Name = escape string, Value = actual string).
 * 
 * @author Tadhg
 *
 */
public class StringParser {

	public static class Builder {

		private boolean					skipEmptySegments	= true;								// Skip segments that are 0 length (default = true).
		private char					escapeCharacter		= 0;
		private List <NameValuePair>	escapeNameValues	= new ArrayList <NameValuePair>();		// Strings that are escaped and need to be transformed into their unescaped form.
		private List <String>			separatorStrings	= new ArrayList <String>();

		public Builder skipEmptySegments (boolean skipEmptySegments) { this.skipEmptySegments = skipEmptySegments; return this; }
		public Builder escapeCharacter (char escapeCharacter) { this.escapeCharacter = escapeCharacter; return this; }
		public Builder escapeNameValues (List <NameValuePair> escapeNameValues) { this.escapeNameValues = escapeNameValues; return this; }
		public Builder separatorStrings (List <String> separatorStrings) { this.separatorStrings = separatorStrings; return this; }
		
		// Do Not Overwrite:
		public Builder separatorStrings (String[] separatorStrings) { this.separatorStrings = Arrays.asList (separatorStrings); return this; }
		
		public StringParser build() { return new StringParser (this); }
	}

	private boolean					skipEmptySegments	= true;									// Skip segments that are 0 length (default = true).
	private char					escapeCharacter		= 0;
	private List <NameValuePair>	escapeNameValues	= new ArrayList <NameValuePair>();		// Strings that are escaped and need to be transformed into their unescaped form.
	private List <String>			separatorStrings	= new ArrayList <String>();

	public StringParser() {}

	public StringParser (List <String> separatorStrings) {
		
		this.separatorStrings = separatorStrings;
	}

	public StringParser (String[] separatorStrings) {
		
		this.separatorStrings = Arrays.asList (separatorStrings);
	}

	public StringParser (boolean skipEmptySegments, char escapeCharacter, List <NameValuePair> escapeNameValues, List <String> separatorStrings) {

		this.skipEmptySegments = skipEmptySegments;
		this.escapeCharacter = escapeCharacter;
		this.escapeNameValues = escapeNameValues;
		this.separatorStrings = separatorStrings;
	}

	private StringParser (Builder builder) {

		this.skipEmptySegments = builder.skipEmptySegments;
		this.escapeCharacter = builder.escapeCharacter;
		this.escapeNameValues = builder.escapeNameValues;
		this.separatorStrings = builder.separatorStrings;
	}

	
	/**
	 * Parse the text using the parse parameters used to instantiate the parse class.
	 * The results are returned as a StringList.
	 * 
	 * Note: A string list will always be returned.
	 * 
	 * @param text
	 * @return
	 */
	public List <String> parse (String text) {
		
		/////////////////////////////////////////////////////////
		// Declarations:
		/////////////////////////////////////////////////////////

		List <String>	resultStringList		= null;
		StringBuilder	segmentStringBuilder	= null;
		int				segmentIndexCurrent		= 0;
		boolean			isEscapeSequence		= false;
		boolean			isSeparator				= false;


		/////////////////////////////////////////////////////////
		// Code:
		/////////////////////////////////////////////////////////

		resultStringList	= new ArrayList <String>();
		
		if (StringUtils.isNotEmpty (text)) {
			
			segmentStringBuilder = new StringBuilder();
			
			// Loop through all characters...
			
			while (segmentIndexCurrent < text.length()) {
				
				if (this.escapeCharacter > 0 && this.escapeCharacter == text.charAt (segmentIndexCurrent) && segmentIndexCurrent < text.length() -1) {
					
					// Escape character found...
					
					// Check is escape sequence...

					isEscapeSequence = false;
					
					for (NameValuePair escapeNVPair: this.escapeNameValues) {
						
						if (StringUtils.isStartsWith (text.substring (segmentIndexCurrent + 1), escapeNVPair.getName())) {
							
							// Match found...
							
							// Add unescaped string...
							segmentStringBuilder.append (escapeNVPair.getValue());
							
							// Skip to the next character...
							segmentIndexCurrent = segmentIndexCurrent + escapeNVPair.getName().length() + 1;
							
							isEscapeSequence = true;
							
							break;
						}
					}
					
					if (! isEscapeSequence) {
					
						// Not an escape sequence.
						
						// Add as normal character...
						segmentStringBuilder.append (text.charAt (segmentIndexCurrent));
						
						// Increment counter...
						segmentIndexCurrent ++;
						
					}
					
				}
				else {
					
					// Check if separator string...
					
					isSeparator = false;
					
					for (String separator: separatorStrings) {
						
						if (StringUtils.isStartsWith (text.substring (segmentIndexCurrent), separator)) {
							
							isSeparator = true;
							break;
						}
					}
					
					if (isSeparator) {
						
						// Segment Separator...
						
						// Save current segment...
						
						if (segmentStringBuilder.length() > 0) {
							
							resultStringList.add (segmentStringBuilder.toString());
							segmentStringBuilder = new StringBuilder();
						}
						else if (! this.skipEmptySegments) {
							
							resultStringList.add ((String) null);
						}
						
						// Increment counter...
						segmentIndexCurrent ++;
						
					}
					else {
						
						// Normal character.
						
						// Add...
						segmentStringBuilder.append (text.charAt (segmentIndexCurrent));
						
						// Increment counter...
						segmentIndexCurrent ++;
					}
				}
			}
			
			if (segmentStringBuilder.length() > 0) {
				
				resultStringList.add (segmentStringBuilder.toString());
			}
		}
		

		return resultStringList;
	}
}
