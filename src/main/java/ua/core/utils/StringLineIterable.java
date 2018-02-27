package ua.core.utils;

import java.util.Iterator;


/**
 * Returns an iterator that iterates over a multiline string.
 * Use primarily to iterate in a for statement.
 * 
 * @author Tadhg
 *
 */
public class StringLineIterable implements Iterable <String> {
	
	private String string;
	
	public StringLineIterable (String string) {
		this.string = string;
	}

	@Override
	public Iterator <String> iterator() {

		return new StringLineIterator (string);
	}

}
