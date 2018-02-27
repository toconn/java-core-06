package ua.core.comp.settings;

import java.util.List;
import java.util.Set;

import ua.core.exceptions.InvalidValue;

public interface Settings {

	public Set<String> getKeys();
	public Set<String> getKeySubset (String rootName);
	
	public boolean getBoolean (String name) throws InvalidValue;
	public boolean getBoolean (String name, boolean defaultValue);
	public int getInt (String name) throws InvalidValue;
	public int getInt (String name, int defaultValue);
	public long getLong (String name) throws InvalidValue;
	public long getLong (String name, long defaultValue);
	public String getString (String name);
	public String getString (String name, String defaultValue);
	
	public boolean hasBoolean (String name);
	public boolean hasInt (String name);
	public boolean hasLong (String name);
	public boolean hasProperty (String name);
	
	public List<String> findMatching (List<String> names);
	public List<String> findMissing (List<String> names);
	
	public void set (String name, boolean value);
	public void set (String name, int value);
	public void set (String name, long value);
	public void set (String name, String value);
}
