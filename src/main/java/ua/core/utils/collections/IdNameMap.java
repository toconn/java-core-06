package ua.core.utils.collections;

import java.util.Collection;
import java.util.List;
import ua.core.data.WithIdName;

public interface IdNameMap <T extends WithIdName> extends Iterable<T> {
	
	public void add (T item);
	public void addAll (T[] items);
	public void addAll (Collection<T> items);
	public T get (int id);
	public T get (String name);
	public List<Integer> getIds();
	public List<String> getNames();
	public boolean hasId (int id);
	public boolean hasName (String name);
	public List<T> getAll();
	public void remove (int id);
	public void remove (String name);
	public void remove (T item);
	public void rename (T item);
	public int size();
}
