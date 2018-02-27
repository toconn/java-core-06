package ua.core.utils.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.core.exceptions.ExceptionMessages;
import ua.core.exceptions.ItemNotFound;
import ua.core.utils.MessageUtils;


public class DirectoryUtils {

	/**
	 * Copies the entire folder from the source directory to the target diretory.
	 * 
	 * @param sourceDirectory
	 * @param targetDirectory
	 * @throws IOException 
	 * @throws ItemNotFound 
	 */
	public static void copyDirectory (String sourceDirectory, String targetDirectory) throws IOException, ItemNotFound {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		File		sourceDirFile	= null;
		File		targetDirFile	= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		sourceDirFile = new File (sourceDirectory);
		targetDirFile = new File (targetDirectory);
		
		copyDirectory (sourceDirFile, targetDirFile);
	}
	
	
	/**
	 * Copies the entire directory to the target directory.
	 * 
	 * @param sourceDirectory
	 * @param targetDirectory
	 * @throws IOException
	 * @throws ItemNotFound 
	 */
	public static void copyDirectory (File sourceDirectory, File targetDirectory) throws IOException, ItemNotFound {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String[]	dirList		= null;

		File		sourceFile	= null;
		File		targetFile	= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (! sourceDirectory.exists())
			
			throw new ItemNotFound (MessageUtils.toString (ExceptionMessages.MESSAGE_FILE_NOT_FOUND, sourceDirectory.getName()));
			
		
		if (sourceDirectory.isDirectory()) {
			
			// Create destination directory...
			
			if (! targetDirectory.exists())
				
				targetDirectory.mkdir();
			
			
			// Read source directory...
			
			dirList = sourceDirectory.list();
			
			// Copy contents...
			
			for (String dirFile: dirList) {
				
				sourceFile = new File (sourceDirectory,	dirFile);
				targetFile = new File (targetDirectory,	dirFile);
				
				copyDirectory (sourceFile, targetFile);
			}
		}
		else
			
			FileUtils.copyFile (sourceDirectory, targetDirectory);
		
	}
	
	
	public static void createDirectory (String path) {

		// //////////////////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////////////////

		File directory = null;

		// //////////////////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////////////////

		directory = new File (path);

		if (!directory.exists()) {

			directory.mkdirs();
		}

	}

	
	/**
	 * Searches a list if paths to find where a directory is located.
	 * Will return either the path to the directory or null if nothing is found.
	 * 
	 * @param pathList
	 * @param directoryName
	 * @return
	 */
	public static String findDirectoryPath (List<String> pathList) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	directoryPath	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (pathName)) {
    		
    			directoryPath = pathName;
    			break;
    		}
    	}
    	
    	return directoryPath;
    }

	
	/**
	 * Searches a list if paths to find where a directory is located.
	 * Will return either the path to the directory or null if nothing is found.
	 * 
	 * @param pathList
	 * @param directoryName
	 * @return
	 */
	public static String findDirectoryPath (List<String> pathList, String directoryName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	directoryPath	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (FileUtils.join (pathName, directoryName))) {
    		
    			directoryPath = pathName;
    			break;
    		}
    	}
    	
    	return directoryPath;
    }
    
    
    /** 
     * Finds one or more paths containing a directory from a list of possible paths.
     * Will return either the path list or null if nothing is found.
     * 
     * @param pathList
     * @param directoryName
     * @return
     */
    public static List<String> findDirectoryPaths (List<String> pathList) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		List<String>	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (pathName)) {
    			
    			if (directoryPathList == null)
    				
    				directoryPathList = new ArrayList<String>();
    		
    			directoryPathList.add (pathName);
    			break;
    		}
    	}
    	
    	return directoryPathList;
    }
    
    
    /** 
     * Finds one or more paths containing a directory from a list of possible paths.
     * Will return either the path list or null if nothing is found.
     * 
     * @param pathList
     * @param directoryName
     * @return
     */
    public static List<String> findDirectoryPaths (List<String> pathList, String directoryName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		List<String>	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (FileUtils.join (pathName, directoryName))) {
    			
    			if (directoryPathList == null)
    				
    				directoryPathList = new ArrayList<String>();
    		
    			directoryPathList.add (pathName);
    			break;
    		}
    	}
    	
    	return directoryPathList;
    }


	/**
	 * Get the directory listing for the directory.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryFiles (String directory) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles());

		return directoryFileList;
	}

	
	/**
	 * Get the directory listing for the directory.
	 * Optionally include subdirectories as well.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryFiles (String directory, boolean includeSubdirectories) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles());
		
		
		// If include subdirectories...
		
		if (includeSubdirectories) {
		
			for (File subFile : directoryFileList) {
				
				if (subFile.isDirectory()) {
					
					directoryFileList.addAll (getDirectoryFiles (subFile.getPath(), includeSubdirectories));
				}
			}
		}
		
		return directoryFileList;
	}


	/**
	 * Return a filtered file listing of the directory.
	 * Optionally includes subdirectories.
	 * 
	 */
	public static List <File> getDirectoryFiles (String directory, boolean includeSubdirectories, FilenameFilter filter) {

		// ////////////////////////////////////////////////////////////////
		// Declarations
		// ////////////////////////////////////////////////////////////////

		List <File>	directoryFiles			= null;
		List <File>	directoryDirectories	= null;

		
		// ////////////////////////////////////////////////////////////////
		// Code
		// ////////////////////////////////////////////////////////////////
		
		// Get the current directory's files...
		
		directoryFiles	= getDirectoryFiles (directory, filter);
		
		
		// Check the subdirectories...
		
		if (includeSubdirectories) {
			
			directoryDirectories = getSubdirectories (directory);
			
			for (File directoryFile : directoryDirectories) {
				
				directoryFiles.addAll (getDirectoryFiles (directoryFile.getPath(), includeSubdirectories, filter));
			}
		}

		return directoryFiles;
	}


	/**
	 * Get the directory listing for the current directory.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryFiles (String directory, FilenameFilter filter) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles (filter));

		return directoryFileList;
	}

	
	/**
	 * Return a file listing of the directory.
	 * Returns a string list only.
	 * 
	 */
	public static List <String> getDirectoryFileStrings (String directory) {

		// ////////////////////////////////////////////////////////////////
		// Declarations
		// ////////////////////////////////////////////////////////////////

		File			directoryFile	= null;
		List<String>	dirStringList	= null;

		
		// ////////////////////////////////////////////////////////////////
		// Code
		// ////////////////////////////////////////////////////////////////

		directoryFile	= new File (directory);
		dirStringList	= Arrays.asList (directoryFile.list());

		return dirStringList;
	}

	
	/**
	 * Return a filtered file listing of the directory.
	 * Returns a string list only.
	 * 
	 */
	public static List<String> getDirectoryFileStrings (String directory, FilenameFilter filter) {

		// ////////////////////////////////////////////////////////////////
		//   Declarations
		// ////////////////////////////////////////////////////////////////

		File			directoryFile	= null;
		List<String>	dirStringList	= null;

		
		// ////////////////////////////////////////////////////////////////
		//   Code
		// ////////////////////////////////////////////////////////////////


		directoryFile	= new File (directory);
		dirStringList	= Arrays.asList (directoryFile.list (filter));

		return dirStringList;
	}
	
	
	/**
	 * Returns a listing of subdirectories only.
	 * 
	 * 
	 * @param directoryName
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getSubdirectories (String directory) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile	= null;
		List <File>	subDirectories	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		subDirectories	= Arrays.asList (directoryFile.listFiles (new FileFilterDirectory()));
		
		return subDirectories;
	}


	/**
	 * Returns a listing of subdirectories only.
	 * 
	 * 
	 * @param directoryName
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getSubdirectories (String directory, boolean includeSubdirectories) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile	= null;
		List <File>	subdirectories	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile	= new File (directory);
		subdirectories	= Arrays.asList (directoryFile.listFiles (new FileFilterDirectory()));
		
		
		// If include subdirectories...
		
		if (includeSubdirectories) {
			for (File subDirFile : subdirectories) {
				subdirectories.addAll (getSubdirectories (subDirFile.getPath(), includeSubdirectories));
			}
		}
		
		return subdirectories;
	}

	
	
	public static boolean isDirectoryExists (String directoryName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		File	directory		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		directory = new File (directoryName);
		
		return (directory.exists() && directory.isDirectory());
	}
}
