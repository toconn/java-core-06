package ua.core.comp.os;

import static ua.core.comp.os.OSConst.*;

import java.util.List;

import ua.core.utils.EnvironmentUtils;
import ua.core.utils.StringUtils;

public class OSLinux extends OSBase implements OS {

	@Override
	public String getFileSeparator () {
		return UNIX_FILE_SEPARATOR;
	}

	@Override
	public String getNewLine () {
		return UNIX_NEW_LINE;
	}

	@Override
	public String getOSName () {
		return LINUX_OS_NAME;
	}

	@Override
	public String getPathSeparator() {
		return UNIX_PATH_SEPARATOR;
	}

	@Override
	public String getUserAppDir() {
		return getUserRootDir();
	}

	@Override
	public String getUserAppDir (String appName) {
		appName = StringUtils.toLowerCase (StringUtils.stripWhitespaces(appName));	// Converts "App Name" -> "appname"
		return normalizePath (getUserAppDir() + UNIX_FILE_SEPARATOR + appName);
	}

	@Override
	public String getUserAppDirEnvVariable() {
		return EnvironmentUtils.asParsible (UNIX_USER_DIR_ENV_VAR) + UNIX_FILE_SEPARATOR;
	}

	@Override
	public String getUserDocDir() {
		return joinEnvValueAndSubpath (LINUX_USER_DIR_ENV_VAR, LINUX_USER_DOC_SUBDIR);
	}

	@Override
	public String getUserDocDir (String appName) {
		return join (getUserDocDir(), appName, true);
	}

	@Override
	public String getUserDocDirEnvVariable () {
		return joinEnvVarAndSubpath (LINUX_USER_DIR_ENV_VAR, LINUX_USER_DOC_SUBDIR);
	}
	
	@Override
	public String getUserRootDir () {
		return EnvironmentUtils.getValue (UNIX_USER_DIR_ENV_VAR);
	}

	@Override
	public String getUserRootDirEnvVariable () {
		return EnvironmentUtils.asParsible (UNIX_USER_DIR_ENV_VAR);
	}

	@Override
	public boolean isLinux () {
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
