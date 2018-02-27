/*
 * Created on May 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ua.core.data;


/**
 * @author TOCONNEL
 *
 * Immutable.
 */
public class NameObjectPair<T> {
	
	private final String	name;
	private final T value;


	/**
	 * @param name
	 * @param value
	 */
	public NameObjectPair (String name, T value) {

		super();
		this.name = name;
		this.value = value;
	}

	
	public String getName() {

		return name;
	}

	public T getValue() {

		return value;
	}

	@Override
	public String toString() {

		return "NameObjectPair [name=" + name + ", value=" + value + "]";
	}
}
