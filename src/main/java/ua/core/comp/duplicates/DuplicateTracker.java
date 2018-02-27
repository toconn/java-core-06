package ua.core.comp.duplicates;

import java.util.List;
import java.util.Map;

public interface DuplicateTracker <Item> {

	public void add (String name, Item item);
	public Map<String,List<Item>> getDuplicatesMap();
	public boolean isDuplicate (String name);
	public boolean isNotDuplicate (String name);
	public boolean hasDuplicates();
}
