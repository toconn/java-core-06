package ua.core.comp.filelocator;

import java.util.ArrayList;
import java.util.List;

import ua.core.comp.os.OS;
import ua.core.exceptions.InvalidConfiguration;
import ua.core.utils.CollectionUtils;
import ua.core.utils.EnvironmentUtils;
import ua.core.utils.StringCollectionUtils;
import ua.core.utils.StringUtils;
import ua.core.utils.file.DirectoryUtils;
import ua.core.utils.file.FileUtils;

import static ua.core.comp.filelocator.FileLocatorConst.*;

public class FileLocatorMain implements FileLocator {
	
	// Code Crank Warning!!!! : Not Copy Paste Replaceable!!!
	// Code Crank Warning!!!! : Not Copy Paste Replaceable!!!
	// Code Crank Warning!!!! : Not Copy Paste Replaceable!!!
	
	public static class Builder {

		private String appConfigFileName;
		private String appConfigPathEnvName;
		private String appSubdirName;
		private boolean includeSharedConfigDirs;
		private boolean includeSharedDocDirs;
		private boolean includeUserConfigDirs;
		private boolean includeUserDocDirs;
		private OS os;

		public Builder appConfigFileName (String appConfigFileName) { this.appConfigFileName = appConfigFileName; return this; }
		public Builder appConfigPathEnvName (String appConfigPathEnvName) { this.appConfigPathEnvName = appConfigPathEnvName; return this; }
		public Builder appSubdirName (String appSubdirName) { this.appSubdirName = appSubdirName; return this; }
		public Builder includeSharedConfigDirs (boolean includeSharedConfigDirs) { this.includeSharedConfigDirs = includeSharedConfigDirs; return this; }
		public Builder includeSharedDocDirs (boolean includeSharedDocDirs) { this.includeSharedDocDirs = includeSharedDocDirs; return this; }
		public Builder includeUserConfigDirs (boolean includeUserConfigDirs) { this.includeUserConfigDirs = includeUserConfigDirs; return this; }
		public Builder includeUserDocDirs (boolean includeUserDocDirs) { this.includeUserDocDirs = includeUserDocDirs; return this; }
		public Builder os (OS os) { this.os = os; return this; }

		public FileLocatorMain build() throws InvalidConfiguration { return new FileLocatorMain (this); }
	}

	private String appConfigFileName;
	private String appConfigPathEnvName;
	private String appSubdirName;
	private boolean includeSharedConfigDirs;
	private boolean includeSharedDocDirs;
	private boolean includeUserConfigDirs;
	private boolean includeUserDocDirs;
	private OS os;

	private FileLocatorMain (Builder builder) throws InvalidConfiguration {

		this.appConfigFileName = builder.appConfigFileName;
		this.appConfigPathEnvName = builder.appConfigPathEnvName;
		this.appSubdirName = builder.appSubdirName;
		this.includeSharedConfigDirs = builder.includeSharedConfigDirs;
		this.includeSharedDocDirs = builder.includeSharedDocDirs;
		this.includeUserConfigDirs = builder.includeUserConfigDirs;
		this.includeUserDocDirs = builder.includeUserDocDirs;
		this.os = builder.os;
		
		validateConfig();
	}

	public String getAppConfigFileName() { return appConfigFileName; }
	public String getAppConfigPathEnvName() { return appConfigPathEnvName; }
	public String getAppSubdirName() { return appSubdirName; }
	public boolean isIncludeSharedConfigDirs() { return includeSharedConfigDirs; }
	public boolean isIncludeSharedDocDirs() { return includeSharedDocDirs; }
	public boolean isIncludeUserConfigDirs() { return includeUserConfigDirs; }
	public boolean isIncludeUserDocDirs() { return includeUserDocDirs; }
	public OS getOs() { return os; }

	public void setAppConfigFileName (String appConfigFileName) { this.appConfigFileName = appConfigFileName; }
	public void setAppConfigPathEnvName (String appConfigPathEnvName) { this.appConfigPathEnvName = appConfigPathEnvName; }
	public void setAppSubdirName (String appSubdirName) { this.appSubdirName = appSubdirName; }
	public void setIncludeSharedConfigDirs (boolean includeSharedConfigDirs) { this.includeSharedConfigDirs = includeSharedConfigDirs; }
	public void setIncludeSharedDocDirs (boolean includeSharedDocDirs) { this.includeSharedDocDirs = includeSharedDocDirs; }
	public void setIncludeUserConfigDirs (boolean includeUserConfigDirs) { this.includeUserConfigDirs = includeUserConfigDirs; }
	public void setIncludeUserDocDirs (boolean includeUserDocDirs) { this.includeUserDocDirs = includeUserDocDirs; }
	public void setOs (OS os) { this.os = os; }

	@Override
	public String toString() {
		return "FileLocatorMain [appConfigFileName=" + appConfigFileName + ", appConfigPathEnvName=" + appConfigPathEnvName + ", appSubdirName=" + appSubdirName + ", includeSharedConfigDirs=" + includeSharedConfigDirs + ", includeSharedDocDirs=" + includeSharedDocDirs + ", includeUserConfigDirs=" + includeUserConfigDirs + ", includeUserDocDirs=" + includeUserDocDirs + "]";
	}


	
	/**
	 * Gets the list of all possibles config directories as configured in the environment variables, etc.
	 * 
	 * Does not verify that the directories exist!
	 * @return
	 */
	@Override
	public List<String> getConfigSearchDirs() {
		
		String			path;
		List<String>	paths;
		List<String>	searchDirs = new ArrayList<>();
	
		
		// Add App Path from environment variables (if found):
		path = getAppConfigPathFromEnv();
		if (path != null) {
			searchDirs.add (path);
		}
		
		// Add path for User Config Dirs environment variables:
		paths = getUserConfigPathsFromEnv();
		if (CollectionUtils.isNotEmpty (paths)) {
			addAndAppendAppSubdirectory (searchDirs, paths);
		}

		// Add OS standard path:
		searchDirs.add (getOSConfigDir());
		
		// Add path for Shared Config Dirs environment variables:
		paths = getSharedConfigPathsFromEnv();
		if (CollectionUtils.isNotEmpty (paths)) {
			addAndAppendAppSubdirectory (searchDirs, paths);
		}
				
		return searchDirs;
	}
	
	/**
	 * Gets the list of all possibles doc directories as configured in the environment variables, etc.
	 * 
	 * Does not verify that the directories exist!
	 * @return
	 */
	@Override
	public List<String> getDocSearchDirs() {
		
		List<String>	paths;
		List<String>	searchDirs = new ArrayList<>();
		
		// Add path for User Doc Dirs environment variables:
		paths = getUserDocPathsFromEnv();
		if (CollectionUtils.isNotEmpty (paths)) {
			searchDirs.addAll (paths);
		}
		
		// Add OS standard path:
		searchDirs.add (os.getUserDocDir (this.appSubdirName));

		// Add path for User Doc Dirs environment variables:
		paths = getSharedDocPathsFromEnv();
		if (CollectionUtils.isNotEmpty (paths)) {
			searchDirs.addAll (paths);
		}
		
		return searchDirs;
	}
	
	@Override
	public String getOSConfigDir() {
		return os.getUserAppDir (this.appSubdirName);
	}

	@Override
	public String locateConfigFileDir () {

		return FileUtils.findFirstDirectoryContainingFile (getConfigSearchDirs(), this.appConfigFileName);
	}

	@Override
	public List<String> locateConfigFileDirs () {

		return FileUtils.findDirectoriesContainingFile (getConfigSearchDirs(), this.appConfigFileName);
	}

	@Override
	public String locateConfigDir() {

		return DirectoryUtils.findDirectoryPath (getConfigSearchDirs());
	}

	@Override
	public List<String> locateConfigDirs () {

		return DirectoryUtils.findDirectoryPaths (getConfigSearchDirs());
	}

	@Override
	public String locateDocDir () {

		return DirectoryUtils.findDirectoryPath (getDocSearchDirs());
	}
	
	/**
	 * Safely adds new patha to the paths list and appends the app subdirectory (app name) to them.
	 * 
	 * It ignores nulls.
	 * 
	 * @param paths
	 * @param newPath
	 * @return
	 */
	private List<String> addAndAppendAppSubdirectory (List<String> paths, List<String> newPaths) {
		
		if (newPaths != null) {
			for (String newPath : newPaths) {
				paths.add (newPath + os.getFileSeparator() + this.appSubdirName);
			}
		}
		
		return paths;
	}
	
	/**
	 * Searches the list of environment variables until it finds on that is set. Returns the value.
	 * 
	 * Will return null if none found
	 * @param envNameArray
	 * @return
	 */
	private String findEnvValue (String[] envNameArray) {
		
		String value = null;
		
		for (String envName : envNameArray) {
			
			value = EnvironmentUtils.getValue (envName);
			
			if (StringUtils.isNotEmpty (value)) {
				break;
			}
		}
		
		return value;
	}
	
	/**
	 * Returns the Application config path by searching for the application specific environment variable.
	 * 
	 * Does not test if the path exists.
	 * @return
	 */
	private String getAppConfigPathFromEnv() {
		
		String path = null;
		
		if (this.appConfigPathEnvName != null) {
			path = EnvironmentUtils.getValue (this.appConfigPathEnvName);
		}
		
		return path;
	}
	
	/**
	 * Finds the environment variable from the list with a value.
	 * Expands the value into a list of paths.
	 * 
	 * Will return null if none found.
	 * 
	 * @param envNameArray
	 * @return
	 */
	private List<String> getPathsActualFromEnv (String[] envNameArray) {
		
		String			pathText;
		List<String>	paths = null;
		
		pathText = findEnvValue (envNameArray);
		
		if (StringUtils.isNotEmpty (pathText)) {
			paths = os.splitPaths (pathText);
		}
		
		return paths;
	}
	
	/**
	 * Gets the list of Shared Config paths from the Shared App Data Dir environment variable.
	 * 
	 * Does not verify that the directories exist!
	 * @return
	 */
	private List<String> getSharedConfigPathsFromEnv() {
		
		List<String>	paths = null;
		
		if (this.includeSharedConfigDirs) {
			paths = getPathsActualFromEnv (ENV_SHARED_CONFIG_ROOT_DIR_ARRAY);
		}
		
		return paths;
	}
	
	/**
	 * Gets the list of Shared doc paths from the Shared Doc Dir environment variable.
	 * 
	 * Does not verify that the directories exist!
	 * @return
	 */
	private List<String> getSharedDocPathsFromEnv() {
		
		List<String>	paths = null;
		
		if (this.includeSharedDocDirs) {
			paths = getPathsActualFromEnv (ENV_SHARED_DOC_ROOT_DIR_ARRAY);
		}
		
		return paths;
	}
	
	/**
	 * Gets the list of User Config paths from the User App Data Dir environment variable.
	 * 
	 * Does not verify that the directories exist!
	 * @return
	 */
	private List<String> getUserConfigPathsFromEnv() {
		
		List<String>	paths = null;
		
		if (this.includeUserConfigDirs) {
			paths = getPathsActualFromEnv (ENV_USER_CONFIG_ROOT_DIR_ARRAY);
		}
		
		return paths;
	}

	/**
	 * Gets the list of User Doc paths from the User Doc Dir environment variable.
	 * 
	 * Does not verify that the directories exist!
	 * @return
	 */
	private List<String> getUserDocPathsFromEnv() {

		List<String>	paths = null;
		
		if (this.includeUserDocDirs) {
			paths = getPathsActualFromEnv (ENV_USER_DOC_ROOT_DIR_ARRAY);
		}
		
		return paths;
	}
	
	private void validateConfig() throws InvalidConfiguration {
		
		List<String> errors = null;
		
		if (StringUtils.isEmpty (this.appSubdirName)) {
			errors = CollectionUtils.add (errors, "Missing appSubdirName property.");
		}

		if (StringUtils.isEmpty (this.appConfigFileName)) {
			errors = CollectionUtils.add (errors, "Missing appConfigFileName property.");
		}
		
		if (this.os == null) {
			errors = CollectionUtils.add (errors, "Missing os property.");
		}
		
		if (errors != null) {
			throw new InvalidConfiguration("FileLocatorMain: " + StringCollectionUtils.join (errors, " "));
		}
	}
}
