package ua.core.utils;

import ua.core.exceptions.Break;

/**
* This is a command that is to be iterated over a collection.
* 
* There may be no results or they may need to be retrieved in a separate call.
* 
* Use CollectionUtils.iterateCommand();
*/
public interface CollectionCommand <Item> {
	
	public void process (Item item) throws Break ;
}
