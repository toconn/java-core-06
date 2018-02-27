package ua.core.utils.file;

import java.util.Collection;

import ua.core.exceptions.Break;
import ua.core.utils.CollectionResultCommand;
import ua.core.utils.CollectionUtils;


/**
 * Finds the location of a file.
 * 
 * Process a list of directories through this command and it will return the first directory that the file is
 * located in. It will return null if not found.
 * 
 * @author Tadhg
 *
 */
public class FileDirectoryLocatorCommand implements CollectionResultCommand <FileDirectory, String> {

	String	fileName;
	String	directoryName;
	
	public FileDirectoryLocatorCommand (String fileName) {
		
		this.fileName = fileName;
	}

	@Override
	public String getResult() {

		return directoryName;
	}
	
	public String process (Collection <FileDirectory> fileDirectoryCollection) {
		
		return CollectionUtils.iterateCommand (fileDirectoryCollection, this);
	}

	@Override
	public void process (FileDirectory item) throws Break {

		if (FileUtils.isFileExists (FileUtils.join (item.getDirectory(), fileName))) {
			
			// directory found...
			// Save and exit loop.
			
			directoryName = item.getDirectory();
			throw new Break();
		}
	}
}
