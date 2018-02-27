package ua.core.comp.settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.core.enums.BooleanType;
import ua.core.exceptions.InvalidValue;
import ua.core.utils.StringUtils;

public abstract class SettingsBase implements Settings {

	@Override
	public List<String> findMatching (List<String> names) {
		
		List<String> matches = new ArrayList<>();
		
		for (String name: names) {
			if (hasProperty (name)) {
				matches.add(name);
			}
		}

		return matches;
	}

	@Override
	public List<String> findMissing(List<String> names) {
		
		List<String> missing = new ArrayList<>();
		
		for (String name: names) {
			if (! hasProperty (name)) {
				missing.add(name);
			}
		}

		return missing;
	}

	@Override
	public boolean getBoolean (String name) throws InvalidValue {

		BooleanType booleanType;
		
		booleanType = BooleanType.getInstance (getString (name));
		if (booleanType == null) {
			throw new InvalidValue("Invalid property value: '" + name + "'.");
		}
		
		return booleanType.getValue();
	}
	
	@Override 
	public boolean getBoolean (String name, boolean defaultValue) {
		
		if (hasBoolean (name)) {
			try {
				return getBoolean (name);
			}
			catch (InvalidValue e) {
				return defaultValue;
			}
		}
		else {
			return defaultValue;
		}
	}
	
	@Override
	public int getInt (String name) throws InvalidValue {

		try {
			return Integer.parseInt (getString (name));
		}
		catch (Exception e) {
			throw new InvalidValue (name);
		}
	}
	
	@Override 
	public int getInt (String name, int defaultValue) {
		
		if (hasBoolean (name)) {
			try {
				return getInt (name);
			}
			catch (InvalidValue e) {
				return defaultValue;
			}
		}
		else {
			return defaultValue;
		}
	}
	
	@Override
	public Set<String> getKeySubset (String name) {

		Set<String> keySetSubset;
		
		keySetSubset = new HashSet<String>();
		
		for (String key: getKeys()) {
			if (StringUtils.isStartsWithIgnoreCase (key, name)) {
				keySetSubset.add (key);
			}
		}
		
		return keySetSubset;
	}

	@Override
	public long getLong (String name) throws InvalidValue {

		try {
			return Long.parseLong (getString (name));
		}
		catch (Exception e) {
			throw new InvalidValue (name);
		}
	}
	
	@Override 
	public long getLong (String name, long defaultValue) {
		
		if (hasBoolean (name)) {
			try {
				return getLong (name);
			}
			catch (InvalidValue e) {
				return defaultValue;
			}
		}
		else {
			return defaultValue;
		}
	}

	@Override
	public String getString (String name) {

		return getItem (name);
	}
	
	@Override 
	public String getString (String name, String defaultValue) {
		
		if (hasProperty (name)) {
			return getString (name);
		}
		else {
			return defaultValue;
		}
	}

	@Override
	public boolean hasBoolean (String name) {

		try {
			getBoolean (name);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean hasInt (String name) {

		try {
			getInt (name);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean hasLong (String name) {

		try {
			getLong (name);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean hasProperty (String name) {

		return hasItem (name);
	}

	@Override
	public void set (String name, boolean value) {

		setItem (name, BooleanType.getInstance (value).toString());
	}

	@Override
	public void set (String name, int value) {

		setItem (name, Integer.toString (value));	
	}

	@Override
	public void set (String name, long value) {

		setItem (name, Long.toString (value));
	}

	@Override
	public void set (String name, String value) {

		setItem (name, value);
	}
	
	/**
	 * This method is called by all the other methods to retrieve the item.
	 * @param name
	 * @param value
	 */
	abstract String getItem (String name);
	
	abstract boolean hasItem (String name);	
	
	/**
	 * This method is called by all the other methods to store the item.
	 * @param name
	 * @param value
	 */
	abstract void setItem (String name, String value);
}
