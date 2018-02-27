package ua.core.comp.settings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Set;

import ua.core.comp.os.OSConst;
import ua.core.exceptions.ExceptionMessages;
import ua.core.exceptions.InternalException;
import ua.core.exceptions.InvalidConfiguration;
import ua.core.exceptions.ItemNotFound;
import ua.core.utils.EnvironmentUtils;
import ua.core.utils.MapIgnoreCase;
import ua.core.utils.StringUtils;

public class SettingsFile extends SettingsBase implements Settings {

	public static final String	FILE_ENCODING				= "UTF-8";

	private static final String	CHARACTERS_WHITE_SPACE		= " \t\r\n\f";
	private static final String	SEPARATORS_KEY_VALUE		= "= \t\r\n\f";
	private static final String SEPARATORS_KEY_VALUE_STRICT	= "=";
	
	private static class ItemProperty {
		
		String value;
		String text;	// The original text of the line.

		public ItemProperty (String text) {

			this.value = null;
			this.text = text;
		}
		
		public ItemProperty (String value, String text) {

			this.value = value;
			this.text = text;
		}
		
		public String getValue() {
		
			return value;
		}
		
		public String getText() {
		
			return text;
		}
	}
	
		
	private MapIgnoreCase <ItemProperty> appPropsMap = new MapIgnoreCase <ItemProperty>();
	private LinkedList <ItemProperty> appPropsLinkedList = new LinkedList <ItemProperty>();
	
	@Override
	public Set<String> getKeys() {

		/*
		 * key should always = .getName()
		 * 
		 * Not sure why I did this this way but I'm replacing it with a simpler version
		
		Set<String> keySet;
		
		keySet = new HashSet <String>();
		
		for (String key: appPropsMap.keySet()) {
			keySet.add (appPropsMap.get (key).getName());
		}
		
		return keySet;
		*/
		
		return appPropsMap.keySet();
	}
	
	/**
	 * Load file from location in environment.
	 * 
	 * The environment name is checked in Java's system properties
	 * and then in the environment. If it exists and points to
	 * a file, the file will be loaded.
	 * 
	 * Otherwise an exception will be thrown.
	 * 
	 * @param environmentName
	 * @throws InvalidConfiguration
	 * @throws ItemNotFound
	 */
	public void loadFromEnvironment (String environmentName) throws InvalidConfiguration, ItemNotFound {
		
		String filePath;
		
		filePath = System.getProperty(environmentName);
		
		
		if (filePath == null) { 
			filePath = System.getenv (environmentName);
		}

		filePath = EnvironmentUtils.evaluate (filePath);
		
		if (StringUtils.isEmpty (filePath)) {
			throw new InvalidConfiguration ("Environment variable '" + environmentName + "' is missing.");
		}
		else {
			load (filePath);
		}
	}
	
	public void load (String filePath) throws ItemNotFound {
		
		try {
			load (new FileInputStream (filePath));
		}
		catch (FileNotFoundException e) {
			throw new ItemNotFound (ExceptionMessages.MESSAGE_FILE_NOT_FOUND, filePath);
		}
		catch (IOException e) {
			throw new InternalException (e);
		}
	}

	public void load (InputStream inputStream) throws IOException {
	
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		BufferedReader	inputReader		= null;
		
		String			inputLine		= null;

		int				inputLineLength;
		int				keyIndex;
		int				separatorIndex;
		int				valueIndex;
	
		char			firstChar;
		char			currentChar;
		
		String			propertyName	= null;
		String			propertyValue	= null;
		
		boolean			lineAdded;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		
		inputReader	= new BufferedReader (new InputStreamReader (inputStream, FILE_ENCODING));
		
		do {
			
			// Get next line...
			
			inputLine = inputReader.readLine();
			lineAdded = false;
			
			if (StringUtils.isNotEmpty (inputLine)) {
				
				// Continue lines that end in slashes if they are not comments
				
				firstChar = inputLine.charAt(0);
				
				if ((firstChar != '#') && (firstChar != '!')) {
	
					// Find start of key
					
					inputLineLength = inputLine.length();
	
					for (keyIndex = 0; keyIndex < inputLineLength; keyIndex++) {
						
						if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt (keyIndex)) == -1) {
							
							break;
						}
					}
	
					// Blank lines are ignored...
					
					if (keyIndex == inputLineLength) {
						
						continue;
					}
	
					// Find separation between key and value...
	
					for (separatorIndex = keyIndex; separatorIndex < inputLineLength; separatorIndex++) {
						
						currentChar = inputLine.charAt (separatorIndex);
						
						if (currentChar == '\\') {
							separatorIndex++;
						}
						else if (SEPARATORS_KEY_VALUE.indexOf (currentChar) != -1) {
							break;
						}
					}
	
					// Skip over whitespace after key if any
	
					for (valueIndex=separatorIndex; valueIndex<inputLineLength; valueIndex++) {
						
						if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt(valueIndex)) == -1) {
							break;
						}
					}
					
					// Skip over one non whitespace key value separators if any...
						
					if (valueIndex < inputLineLength) {
						
						if (SEPARATORS_KEY_VALUE_STRICT.indexOf (inputLine.charAt(valueIndex)) != -1) {
							valueIndex++;
						}
					}
	
					// Skip over white space after other separators if any...
						
					while (valueIndex < inputLineLength) {
						
						if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt (valueIndex)) == -1) {
							break;
						}
						
						valueIndex++;
					}
	
					propertyName	= inputLine.substring (keyIndex, separatorIndex);
					propertyValue	= (separatorIndex < inputLineLength) ? inputLine.substring (valueIndex, inputLineLength) : "";
	
					// Convert key and value...
					
					// propertyName	= convertEscapedCharacters (propertyName);
					// propertyValue = convertEscapedCharacters (propertyValue);
					
					setItem (propertyName, propertyValue, inputLine);
					lineAdded = true;
				}
			}
			
			if (! lineAdded) {
				setNonItem (inputLine);
			}
		}
		while (inputLine != null);
	}
	
	
	public void save (String fileName) throws InternalException {
		
		try {
			
			save (new FileOutputStream (fileName));
		}
		catch (IOException e) {
			
			throw new InternalException (e);
		}
	}

	
	
	public void save (OutputStream outputStream) throws IOException {
		
		byte[] textBytes;
		byte[] newLineBytes;
		
		newLineBytes = OSConst.WINDOWS_NEW_LINE.getBytes();
	
		for (ItemProperty item: appPropsLinkedList) {
			
			textBytes = item.getText().getBytes();
			outputStream.write (textBytes);
			outputStream.write (newLineBytes);
		}

		outputStream.flush();
		outputStream.close();		
	}


	@Override
	String getItem (String name) {

		ItemProperty item;
		
		item = appPropsMap.get (name);
		
		if (item != null) {
			return item.getValue();
		}
		else {
			return null;
		}
	}

	@Override
	boolean hasItem (String name) {

		return appPropsMap.containsKey (name);
	}

	/**
	 * Store normal name and matching value.
	 * 
	 */
	@Override
	void setItem (String name, String value) {

		ItemProperty newItem;
		ItemProperty currentItem;
		
		newItem = new ItemProperty (value, name + " = " + value);
		
		if (! hasItem (name)) {
			appPropsMap.add (name, newItem);
			appPropsLinkedList.add (newItem);
		}
		else {
			appPropsMap.add (name, newItem);
			currentItem = appPropsMap.get (name);			
			appPropsLinkedList.set (appPropsLinkedList.indexOf (currentItem), newItem);
		}
	}
	
	/**
	 * Stores the name, value and original format of the line.
	 * Formatting is preserved so that the file is recreated pretty much as the user wanted it.
	 * 
	 * @param name
	 * @param value
	 * @param text
	 */
	void setItem (String name, String value, String text) {

		ItemProperty newItem;
		
		newItem = new ItemProperty (value, text);
		appPropsMap.add (name, newItem);
		appPropsLinkedList.add (newItem);
	}
	
	/**
	 * Store a non-property line.
	 * 
	 * Stored to that if the file is written out, comments and spacing can be preserved.
	 * 	
	 * @param text
	 */
	void setNonItem (String text) {

		ItemProperty newItem;
		
		newItem = new ItemProperty (text);
		appPropsLinkedList.add (newItem);		
	}
}
