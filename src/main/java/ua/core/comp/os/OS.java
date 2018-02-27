package ua.core.comp.os;

import java.util.List;

public interface OS {

	public String getFileSeparator();
	public String getNewLine();
	public String getOSName();
	public String getPathSeparator();
	public String getUserAppDir();
	public String getUserAppDirEnvVariable();
	public String getUserAppDir (String appName);
	public String getUserDocDir();
	public String getUserDocDir (String appName);
	public String getUserDocDirEnvVariable();
	public String getUserRootDir();
	public String getUserRootDirEnvVariable();
	public String joinPath (String rootPath, String subPath);
	public boolean isLinux();
	public boolean isMac();
	public boolean isWindows();
	public String normalizePath (String path);
	public List<String> splitPaths (String paths);
	public List<String> splitPathsAndAppend (String paths, String subpath);
}
