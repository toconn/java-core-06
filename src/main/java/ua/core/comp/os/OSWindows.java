package ua.core.comp.os;

import static ua.core.comp.os.OSConst.*;

import java.util.List;

import ua.core.utils.EnvironmentUtils;

public class OSWindows extends OSBase implements OS {

	@Override
	public String getFileSeparator () {
		return WINDOWS_FILE_SEPARATOR;
	}

	@Override
	public String getNewLine () {
		return WINDOWS_NEW_LINE;
	}

	@Override
	public String getOSName () {
		return WINDOWS_OS_NAME;
	}

	@Override
	public String getPathSeparator() {
		return WINDOWS_PATH_SEPARATOR;
	}

	@Override
	public String getUserAppDir () {
		return EnvironmentUtils.getValue (WINDOWS_USER_APP_DIR_ENV_VAR);
	}

	@Override
	public String getUserAppDir (String appName) {
		return joinEnvValueAndSubpath (WINDOWS_USER_APP_DIR_ENV_VAR, appName);
	}

	@Override
	public String getUserAppDirEnvVariable() {
		return EnvironmentUtils.asParsible (WINDOWS_USER_APP_DIR_ENV_VAR);
	}

	@Override
	public String getUserRootDir () {
		return EnvironmentUtils.getValue (WINDOWS_USER_DIR_ENV_VAR);
	}

	@Override
	public String getUserRootDirEnvVariable () {
		return EnvironmentUtils.asParsible (WINDOWS_USER_DIR_ENV_VAR);
	}

	@Override
	public String getUserDocDir() {
		return joinEnvValueAndSubpath (WINDOWS_USER_DIR_ENV_VAR, WINDOWS_USER_DOC_SUBDIR);
	}

	@Override
	public String getUserDocDir (String appName) {
		return join (getUserDocDir(), appName, true);
	}

	@Override
	public String getUserDocDirEnvVariable () {
		return joinEnvVarAndSubpath (WINDOWS_USER_DIR_ENV_VAR, WINDOWS_USER_DOC_SUBDIR);
	}

	@Override
	public boolean isWindows () {
		return true;
	}
	
	@Override
	public String joinPath (String rootPath, String subPath) {
		return super.join (rootPath, subPath, true);
	}
	
	@Override
	public String normalizePath (String path) {
		return path.replace (UNIX_FILE_SEPARATOR, WINDOWS_FILE_SEPARATOR);
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
