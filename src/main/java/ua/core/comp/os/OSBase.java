package ua.core.comp.os;

import java.util.List;

import ua.core.utils.EnvironmentUtils;
import ua.core.utils.StringCollectionUtils;
import ua.core.utils.StringUtils;

public abstract class OSBase implements OS {

	@Override
	public boolean isLinux () {
		return false;
	}

	@Override
	public boolean isMac () {
		return false;
	}
	
	@Override
	public boolean isWindows () {
		return false;
	}

	@Override
	public List<String> splitPaths (String paths) {
		return StringCollectionUtils.removeBlanks (StringUtils.toWords (paths, OSConst.PATH_SEPARATORS_REGEX));
	}
	
	@Override
	public List<String> splitPathsAndAppend (String paths, String subpath) {
		
		List<String> pathList;
		
		pathList = splitPaths (paths);
		
		for (int i = 0; i < pathList.size(); i++) {
			pathList.set (i, join (pathList.get(i), subpath, true));
		}
		
		return pathList;
	}
	
	/*
	 * Joins two paths together. Will only normalize if asked to.
	 * 
	 * Why boolean?: Because joins internal to the class are already in correct form.
	 */
	protected String join (String rootPath, String subPath, boolean normalize) {

		if (normalize) {
			return normalizePath (rootPath + getFileSeparator() + subPath);
		}
		else {
			return rootPath + getFileSeparator() + subPath;
		}
	}

	protected String joinEnvValueAndSubpath (String envVariable, String subpath) {
		return join (EnvironmentUtils.getValue (envVariable), subpath, false);
	}

	protected String joinEnvVarAndSubpath (String envVariable, String subpath) {
		return join (EnvironmentUtils.asParsible (envVariable), subpath, false);
	}
}
