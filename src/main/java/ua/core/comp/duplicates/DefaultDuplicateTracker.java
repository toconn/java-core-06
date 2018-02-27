package ua.core.comp.duplicates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDuplicateTracker <Item> implements DuplicateTracker <Item>{

	private Map<String, Item> itemMap = new HashMap<>();
	private Map<String, List<Item>> duplicateItemsMap = new HashMap<>();
	
	@Override
	public void add(String name, Item item) {
		
		List<Item> duplicate_items;

		if (! this.itemMap.containsKey(name)) {

			this.itemMap.put(name, item);
		}
		else {

			// Duplicate:
			
			if (this.duplicateItemsMap.containsKey(name)) {
				duplicate_items = this.duplicateItemsMap.get(name);
			}
			else {
				duplicate_items = new ArrayList<>();
				duplicate_items.add(this.itemMap.get(name));
				this.duplicateItemsMap.put(name, duplicate_items);
			}
			
			duplicate_items.add(item);
		}
	}

	@Override
	public Map<String, List<Item>> getDuplicatesMap() {
		return this.duplicateItemsMap;
	}
	
	@Override
	public boolean isDuplicate (String name) {
		return this.itemMap.containsKey(name);
	}

	@Override
	public boolean isNotDuplicate(String name) {
		return ! this.isDuplicate(name);
	}

	@Override
	public boolean hasDuplicates() {
		return this.duplicateItemsMap.size() > 0;
	}

}
