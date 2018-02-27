package ua.core.comp.os;

import java.util.Arrays;
import java.util.List;


public interface OSConst {

	public static final String LINUX_OS_NAME				= "Linux";
	public static final String LINUX_USER_DIR_ENV_VAR		= "HOME";							// /Users/username
	public static final String LINUX_USER_DOC_SUBDIR		= "Documents";						// /Users/username/Documents	(Ubuntu. Assume is same for all)
	
	public static final String OSX_OS_NAME					= "Mac OS X";
	public static final String OSX_USER_APP_SUBDIR			= "Library/Application Support";	// /Users/username/Library/Application Support
	public static final String OSX_USER_DOC_SUBDIR			= "Documents";						// /Users/username/Documents
	public static final String OSX_USER_DIR_ENV_VAR			= "HOME";							// /Users/username
	
	public static final String UNIX_FILE_SEPARATOR			= "/";
	public static final String UNIX_NEW_LINE				= "\n";
	public static final String UNIX_PATH_SEPARATOR			= ":";
	public static final String UNIX_USER_DIR_ENV_VAR		= "HOME";							// /Users/username
	
	public static final String WINDOWS_OS_NAME				= "Windows";
	public static final String WINDOWS_FILE_SEPARATOR		= "\\";
	public static final String WINDOWS_NEW_LINE				= "\r\n";
	public static final String WINDOWS_PATH_SEPARATOR		= ";";
	public static final String WINDOWS_USER_APP_DIR_ENV_VAR	= "APPDATA";				// C:\Users\UserName\AppData\Roaming
	public static final String WINDOWS_USER_DIR_ENV_VAR		= "HOME";							// C:\Users\UserName
	public static final String WINDOWS_USER_DOC_SUBDIR		= "Documents";						// C:\Users\UserName\Documents
	
	public static final List <String> FILE_SEPARATORS = Arrays.asList (new String [] {UNIX_FILE_SEPARATOR, WINDOWS_FILE_SEPARATOR});
	public static final List <String> PATH_SEPARATORS = Arrays.asList (new String [] {UNIX_PATH_SEPARATOR, WINDOWS_PATH_SEPARATOR});
	
	public static final String	PATH_SEPARATORS_REGEX		= "[" + UNIX_PATH_SEPARATOR + WINDOWS_PATH_SEPARATOR + "]+";
}
