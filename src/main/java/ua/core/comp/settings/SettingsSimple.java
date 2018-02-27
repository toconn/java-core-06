package ua.core.comp.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.core.utils.MapIgnoreCase;

public class SettingsSimple extends SettingsBase implements Settings {

	private Map <String, String> appPropsMap = new MapIgnoreCase <String>();

	
	public SettingsSimple() {
	}

	public SettingsSimple (String [][] appPropsArray) {
		
		for (String[] appPropPair : appPropsArray) {
			setItem (appPropPair[0], appPropPair[1]);
		}
	}
	
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
	public Set <String> getKeys() {
		return appPropsMap.keySet();
	}
	
	@Override
	String getItem (String name) {
		return appPropsMap.get (name);
	}
		
	@Override
	boolean hasItem (String name) {
		return appPropsMap.containsKey (name);
	}
	
	@Override
	void setItem (String name, String value) {

		appPropsMap.put (name, value);
	}
}
