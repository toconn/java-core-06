package ua.core.comp.os;

import static ua.core.comp.os.OSConst.*;

import java.util.List;

import ua.core.utils.EnvironmentUtils;

public class OSMac extends OSBase implements OS {

	@Override
	public String getFileSeparator() {
		return UNIX_FILE_SEPARATOR;
	}

	@Override
	public String getNewLine() {
		return UNIX_NEW_LINE;
	}

	@Override
	public String getOSName() {
		return OSX_OS_NAME;
	}

	@Override
	public String getPathSeparator() {
		return UNIX_PATH_SEPARATOR;
	}

	@Override
	public String getUserAppDir() {
		return getUserRootDir() + UNIX_FILE_SEPARATOR + OSX_USER_APP_SUBDIR;
	}

	@Override
	public String getUserAppDir (String appName) {
		return normalizePath (getUserAppDir() + UNIX_FILE_SEPARATOR + appName);
	}

	@Override
	public String getUserAppDirEnvVariable() {
		return EnvironmentUtils.asParsible (OSX_USER_DIR_ENV_VAR) + UNIX_FILE_SEPARATOR + OSX_USER_APP_SUBDIR;
	}

	@Override
	public String getUserDocDir() {
		return joinEnvValueAndSubpath (OSX_USER_DIR_ENV_VAR, OSX_USER_DOC_SUBDIR);
	}

	@Override
	public String getUserDocDir (String appName) {
		return join (getUserDocDir(), appName, true);
	}

	@Override
	public String getUserDocDirEnvVariable () {
		return joinEnvVarAndSubpath (OSX_USER_DIR_ENV_VAR, OSX_USER_DOC_SUBDIR);
	}
	
	@Override
	public String getUserRootDir() {
		return EnvironmentUtils.getValue (OSX_USER_DIR_ENV_VAR);
	}

	@Override
	public String getUserRootDirEnvVariable() {
		return EnvironmentUtils.asParsible(OSX_USER_DIR_ENV_VAR);
	}

	@Override
	public boolean isMac () {
		return true;
	}
	
	@Override
	public String joinPath (String rootPath, String subPath) {
		return super.join (rootPath, subPath, true);
	}
	
	@Override
	public String normalizePath (String path) {
		return path.replace (WINDOWS_FILE_SEPARATOR, UNIX_FILE_SEPARATOR);
	}
	
	@Override
	public List<String> splitPaths (String paths) {
		return super.splitPaths(paths);
	}
	
	@Override
	public List<String> splitPathsAndAppend (String paths, String subpath) {
		return super.splitPathsAndAppend (paths, subpath);
	}
}
