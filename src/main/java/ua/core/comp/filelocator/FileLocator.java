package ua.core.comp.filelocator;

import java.util.List;

/**
 * Locates a file or directory by looking in a number of places
 * 
 * For Config Files:
 * 
 * 		App Environment Var
 * 		User Config Dir/App Subdir
 * 		Os Default User Config Dir/App Subdir
 * 		Shared Config Dir/App Subdir		
 * 
 * For Document Dir:
 * 
 * 		User Doc Dir/App Subdir
 * 		OS Doc Dir/App Subdir
 *
 */
public interface FileLocator {
	
	List<String> getConfigSearchDirs();
	List<String> getDocSearchDirs();
	String getOSConfigDir();
	
	String locateConfigFileDir();
	List<String> locateConfigFileDirs();
	String locateConfigDir();
	List<String> locateConfigDirs();

	String locateDocDir();
}
