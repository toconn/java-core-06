package ua.core.utils.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ua.core.data.WithIdName;
import ua.core.exceptions.ItemNotFound;
import ua.core.utils.CollectionUtils;
import ua.core.utils.MapIgnoreCase;

public class BaseIdNameMap <T extends WithIdName> implements IdNameMap<T> {
	
	private Map<Integer, T> itemIdMap = new HashMap<>();
	private Map<String, T> itemNameMap = new MapIgnoreCase<>();
	
	public BaseIdNameMap() {
	}
	
	public BaseIdNameMap (T[] items) throws ItemNotFound {
		addAll (items);
	}

	public BaseIdNameMap (Collection<T> items) throws ItemNotFound {
		addAll (items);
	}

	@Override
	public void add (T item) {
		this.itemIdMap.put (item.getId(), item);
		this.itemNameMap.put (item.getName(), item);
	}
	
	@Override
	public void addAll (T[] items) {
		
		for (T item : items) {
			add (item);
		}
	}

	@Override
	public void addAll (Collection<T> items) {
		
		for (T item : items) {
			add (item);
		}
	}

	@Override
	public T get (int id) {
		return this.itemIdMap.get (id);
	}

	@Override
	public T get (String name) {
		return this.itemNameMap.get (name);
	}

	@Override
	public List<T> getAll() {
		return CollectionUtils.asSortedList (this.itemNameMap);
	}
	
	@Override
	public List<Integer> getIds() {
		return CollectionUtils.asSortedList (this.itemIdMap.keySet());
	}
	
	@Override
	public List<String> getNames() {
		return CollectionUtils.asSortedList (this.itemNameMap.keySet());
	}
	
	@Override
	public boolean hasId (int id) {
		return this.itemIdMap.containsKey(id);
	}
	
	@Override
	public boolean hasName (String name) {
		return this.itemNameMap.containsKey (name);
	}

	@Override
	public Iterator<T> iterator() {
		return getAll().iterator();
	}

	@Override
	public void remove(int id) {
		remove (this.itemIdMap.get (id));
	}

	@Override
	public void remove(String name) {
		remove (this.itemNameMap.get (name));
	}

	@Override
	public void remove (T item) {
		this.itemIdMap.remove (item.getId());
		this.itemNameMap.remove (item.getName());
	}
	
	@Override
	public void rename (T item) {
		remove (item.getId());
		add (item);
	}
	
	@Override
	public int size() {
		return this.itemIdMap.size();
	}

}
