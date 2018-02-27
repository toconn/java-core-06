/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.utils.file;

import java.io.*;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ua.core.comp.os.OSConst;
import ua.core.data.NameValuePair;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.utils.CollectionUtils;
import ua.core.utils.IsFirst;
import ua.core.utils.SystemUtils;
import ua.core.utils.StringParser;
import ua.core.utils.StringUtils;


/**
 * @author TOCONNEL Created on Jul 27, 2004
 * 
 */
public class FileUtils {

	public static final void copyFile (File sourceFile, File destinationFile) throws IOException {
		
		// Note:
		
		// File Path = full file name and directory
		// File Name = name of the file without the directory information.
		// File Dir is the directory part of the path with no name.
		

		// //////////////////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////////////////

		FileInputStream sourceInputStream	= null;
		FileChannel sourceFileChannel		= null;
		FileOutputStream destinationOutputStream	= null;
		FileChannel destinationFileChannel	= null;

		
		// //////////////////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////////////////

		if (! destinationFile.exists()) {
			destinationFile.createNewFile();
		}

		try {

			sourceInputStream = new FileInputStream (sourceFile);
			sourceFileChannel = sourceInputStream.getChannel();
			destinationOutputStream = new FileOutputStream (destinationFile);
			destinationFileChannel = destinationOutputStream.getChannel();
			destinationFileChannel.transferFrom (sourceFileChannel, 0, sourceFileChannel.size());

			destinationFile.setLastModified (sourceFile.lastModified());
		}
		finally {

			if (sourceFileChannel != null) {
				sourceFileChannel.close();
			}

			if (sourceInputStream != null) {
				sourceInputStream.close();
			}

			if (destinationFileChannel != null) {
				destinationFileChannel.close();
			}

			if (destinationOutputStream != null) {
				destinationOutputStream.close();
			}
		}
	}


	/**
	 * Deletes a file. It checks first to see that the file actually exists.
	 * 
	 * @param filePath
	 */
	public static void delete (String filePath) {
		
		File file = new File (filePath);
		
		if (file.exists()) {
			
			file.delete();
		}
	}


	/**
     * Takes a string of ";" separated path and return in a StringList.
     * 
     * @param pathString
     * @return
     */
    public static List<String> expandPathToList (String pathString) {
    	
    	if (pathString != null) {
    	
    		return (new StringParser(OSConst.PATH_SEPARATORS)).parse (pathString);
    	}
    	else {
    		
    		return null;
    	}
    }
    
    
    /** 
     * Finds one or more paths containing a file from a list of possible paths.
     * 
     * Does not search subdirectories.
     * Will return either the path list or null if nothing is found.
     * 
     * @param directoryPaths
     * @param fileName
     * @return
     */
    public static List<String> findDirectoriesContainingFile (List<String> directoryPaths, String fileName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		List<String>	fileDirList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: directoryPaths) {
    		
    		if (isFileExists (join (pathName, fileName))) {
    			
    			if (fileDirList == null) {
    				fileDirList = new ArrayList <String>();
    			}
    		
    			fileDirList.add (pathName);
    			break;
    		}
    	}
    	
    	return fileDirList;
    }
    
    
    /** 
     * Searches the list of paths attempting to locate a file.
     * Returns the first successful match.
     * 
     * Does not search subdirectories.
     * Will return either the path or null if nothing is found.
     * 
     * @param dirList
     * @param fileName
     * @return
     */
    public static String findFirstDirectoryContainingFile (List<String> dirPaths, String fileName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	fileDirectory	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String path: dirPaths) {
    		
    		if (isFileExists (join (path, fileName))) {
    		
    			fileDirectory = path;
    			break;
    		}
    	}
    	
    	return fileDirectory;
    }
	
    /**
     * Returns the directory to a file in the class path.
     * 
     * @param fileName
     * @return
     * @throws ExceptionRuntime
     */	
    public static String findFileDirInClassPath (String fileName) throws ExceptionRuntime {

		// ////////////////////////////////////////////////////////////////////////////////////
		// Declarations:
		// ////////////////////////////////////////////////////////////////////////////////////

		NameValuePair dummyClass = null; // Used to get class loader.

		URL fileURL = null;
		String filePath = null;

		// ////////////////////////////////////////////////////////////////////////////////////
		// Code:
		// ////////////////////////////////////////////////////////////////////////////////////

		if (fileName != null) {

			try {

				dummyClass = new NameValuePair (null, null);
				fileURL = dummyClass.getClass().getClassLoader().getResource (fileName);

				if (fileURL != null) {
					filePath = URLDecoder.decode (fileURL.getPath(), FileConst.FILE_ENCODING_UTF8);
				}

				if (filePath != null) {
					filePath = FileUtils.getParentPath (filePath);
				}
			}
			catch (Exception e) {

				throw new ExceptionRuntime (e);
			}
		}

		return filePath;
	}

    /**
     * Returns the full path to a file in the class path.
     * 
     * @param fileName
     * @return
     * @throws ExceptionRuntime
     */
    public static String findFilePathInClassPath (String fileName) throws ExceptionRuntime {

		// ////////////////////////////////////////////////////////////////////////////////////
		// Declarations:
		// ////////////////////////////////////////////////////////////////////////////////////

		NameValuePair dummyClass = null; // Used to get class loader.

		URL fileURL = null;
		String filePath = null;

		// ////////////////////////////////////////////////////////////////////////////////////
		// Code:
		// ////////////////////////////////////////////////////////////////////////////////////

		if (fileName != null) {

			try {

				dummyClass = new NameValuePair (null, null);
				fileURL = dummyClass.getClass().getClassLoader().getResource (fileName);

				if (fileURL != null) {
					filePath = URLDecoder.decode (fileURL.getPath(), FileConst.FILE_ENCODING_UTF8);
				}
			}
			catch (Exception e) {

				throw new ExceptionRuntime (e);
			}
		}

		return filePath;
	}
    
    /**
     * Returns the java canonical form of the path.
     * 
     * @param path
     * @return
     * @throws IOException
     */
	public static String getCanonicalPath (String path) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File file = null;
		String newPath = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file = new File (path);
		newPath = file.getCanonicalPath();

		return newPath;
	}
	
	
	public static String getExtension (String fileName) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		int		extensionSepIndex	= 0;
		String	fileExtension		= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		if (fileName != null) {
			
			extensionSepIndex = fileName.lastIndexOf (FileConst.FILE_EXTENSION_SEPARATOR);
			
			if (extensionSepIndex != -1 && extensionSepIndex < fileName.length() - 1) {
				
				fileExtension = fileName.substring (extensionSepIndex + 1);
			}
		}

		return fileExtension;
	}
	
	/**
	 * Returns the base name of a file name.
	 * File name = 'path/baseName.ext'. Returns 'baseName'.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getBaseName (File file) {
		return getBaseName (file.getAbsolutePath());
	}
	
	/**
	 * Returns the base name of a file name.
	 * File name = 'path/baseName.ext'. Returns 'baseName'.
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getBaseName (String filePath) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		int		extensionSepIndex	= 0;
		String	baseName			= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		if (filePath != null) {
			
			// Strip out path:
			
			filePath = getFileName (filePath);
			
			// Strip extension:
			
			extensionSepIndex = filePath.lastIndexOf (FileConst.FILE_EXTENSION_SEPARATOR);
			
			if (extensionSepIndex > 0) {
				baseName = filePath.substring (0, extensionSepIndex);
			}
			else {
				baseName = filePath;
			}
		}

		return baseName;
	}


	/**
	 * Returns a list of file names given a list of directories and one file name.
	 * 
	 * Used to get the full file path for the same file in multiple paths.
	 * 
	 * @param directoryList
	 * @param fileName
	 */
	public static List<String> getFileNames (List<String> directoryNames, String fileName) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

        List <String> fileNameList			= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
        
        if (CollectionUtils.isNotEmpty (directoryNames)) {
        	
        	fileNameList = new ArrayList<String>();
        	
        	for (String directoryName: directoryNames) {
        		
        		fileNameList.add (FileUtils.join (directoryName, fileName));
        	}
        	
            return fileNameList;

        }
        else {

            return null;

        }
	}
	
	/**
	 * Returns the file name of a file.
	 * File name = 'path/baseName.ext'. Returns 'baseName.ext'.
	 */
	public static String getFileName (String filePath) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		int		separatorIndex	= 0;
		String	fileName		= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		if (filePath != null) {
			
			separatorIndex = filePath.lastIndexOf (File.separator);
			
			if (separatorIndex > 0) {
				fileName = filePath.substring (separatorIndex + 1);
			}
			else {
				fileName = filePath;
			}
		}

		return fileName;
	}
	
	
	/**
	 * Returns the parent of the path.
	 * 
	 * It must return a directory.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String getParentPath (String path) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File currentFile = null;
		String currentPath = null;

		File parentFile = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		currentFile = new File (path);
		currentPath = currentFile.getCanonicalPath();

		currentFile = new File (currentPath);
		parentFile = currentFile.getParentFile();

		if (parentFile != null) {

			return parentFile.getCanonicalPath();
		}
		else {

			return null;
		}
	}
	
	
	/**
	 * Returns the file directory without the file name.
	 * 
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getPathDirectory (String filePath) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File	file		= null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file	= new File (filePath);
		
		return file.getParent();
	}	


	/**
	 * Returns the file name only (not full path, no directory).
	 * 
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getPathName (String filePath) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File	file		= null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file	= new File (filePath);
		
		return file.getName();
	}


	/**
	 * Checks to see if the path is an absolute path.
	 * 
	 * Will return false for a relative path.
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isAbsolutePath (String path) {
		
		if (StringUtils.isNotEmpty (path)) {

			if (! SystemUtils.isOSWindows()) {

				// Check unix file name...

				if (StringUtils.isEqual (path.charAt(0), File.separatorChar)) {
					return true;
				}
			}
			else {

				// Check dos file name...

				if (StringUtils.isEqual (path.charAt (0), File.separatorChar)) {
					return true;
				}
				else if (path.length() > 2 && ":".equals (path.substring (1, 2))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks to see if a file exists.
	 * 
	 * Does not check to see what kind of file (file, link, directory).
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExists (String fileName) {

		return (new File (fileName)).exists();
	}
	
	/**
	 * Returns the path (file directory + separator + subpath + ...).
	 * 
	 * Returns the correct format for the os.
	 * 
	 * @param directoryName
	 * @param fileName
	 * @return
	 */
	public static String join (String... subpaths) {
		
		StringBuilder fullPathBuffer = new StringBuilder();;
		IsFirst first = new IsFirst();
		
		for (String subPath : subpaths) {
			
			if (first.isNotFirst()) {
				fullPathBuffer.append(File.separator);
			}
			fullPathBuffer.append(subPath);
		}
		
		return fullPathBuffer.toString();
	}
	
	public static void move (String sourcePath, String targetPath) throws IOException {
		
		Files.move (Paths.get (sourcePath), Paths.get(targetPath));
	}
	
	public static String readToText (File file) throws FileNotFoundException {

		if (file.length() > 0) {
			return readToText (file.getAbsolutePath());
		}
		else {
			return "";
		}
	}
	
	public static String readToText (String filePath) throws FileNotFoundException {
		
		String text = "";
		
		try (Scanner scanner = new Scanner (new File (filePath))) {
			text = scanner.useDelimiter ("\\Z").next();
		}
		catch (NullPointerException e) {
			// Empty file (most likely)
		}
		
		return text;
	}
}
