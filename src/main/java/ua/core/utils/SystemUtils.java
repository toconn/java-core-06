package ua.core.utils;

import java.io.File;
import java.util.List;

public class SystemUtils {
	
	public static final String	JAVA_PROPERTIES_APP_DIR	= "user.dir";
	public static final String	JAVA_PROPERTIES_NEWLINE	= "line.separator";
	public static final String	JAVA_PROPERTIES_OS_NAME	= "os.name";
	
	public static final String	LINUX_OS_NAME			= "Linux";
	public static final String	OSX_OS_NAME				= "Mac OS X";
	public static final String	WINDOWS_OS_NAME			= "Windows";
	
	public static String getLineSeparator() {
		return System.getProperty (JAVA_PROPERTIES_NEWLINE);
	}
	
	public static String getFileSeparator() {
		return File.separator;
	}
	
	public static String getNewline() {
		return getLineSeparator();
	}
	
	public static String getOSName() {
		return System.getProperty (JAVA_PROPERTIES_OS_NAME);
	}
	
	public static String getPathSeparator() {
		return File.pathSeparator;
	}
	
    /**
     * Return the working directory (the directory where the program was launched from).
     * 
     * @return
     */
	public String getWorkingDir() {	
		return System.getProperty (JAVA_PROPERTIES_APP_DIR);
	}

	public static boolean isOSLinux() {
		return StringUtils.isEqualIgnoreCase (getOSName(), LINUX_OS_NAME);
	}

	public static boolean isOSOsx() {
		return StringUtils.isEqualIgnoreCase (getOSName(), OSX_OS_NAME);
	}

	public static boolean isOSWindows() {
		return StringUtils.isStartsWith (getOSName(), WINDOWS_OS_NAME);
	}
	
	public static void printSystemProps () {
		
		List <String> sortedKeyList;
		sortedKeyList = CollectionUtils.sort (System.getProperties().stringPropertyNames());
		
		for (String key: sortedKeyList) {
			System.out.println (StringUtils.pad (key, 24) + ": " + System.getProperty (key));
		}
	}

	public static void sleep (long milliseconds) {
		
		try {
			Thread.sleep (milliseconds);
		}
		catch (InterruptedException e) {
			// Ignore.
		}
	}
}
