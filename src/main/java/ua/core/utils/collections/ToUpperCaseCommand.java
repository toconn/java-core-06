package ua.core.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ua.core.utils.CollectionResultCommand;
import ua.core.utils.CollectionUtils;
import ua.core.utils.StringUtils;


/**
 * Returns a list of all upper case strings.
 */
public class ToUpperCaseCommand implements CollectionResultCommand <String, List<String>> {

	List <String> intList = new ArrayList <String>();
	
	
	@Override
	public List <String> getResult() {
		
		return intList;
	}
	
	
	@Override
	public void process (String item) {

		intList.add (StringUtils.toUpperCase (item));
	}
	
	public List <String> process (Collection <String> items) {
		
		return CollectionUtils.iterateCommand (items, this);
	}
}