package ua.core.comp.filelocator;


public class FileLocatorConst {

	public static final String	ENV_SHARED_CONFIG_ROOT_DIR			= "Shared_App_Data_Dir";
	public static final String	ENV_SHARED_CONFIG_ROOT_DIR_ALT		= "SHARED_APP_DATA_DIR";
	public static final String	ENV_SHARED_CONFIG_ROOT_DIR_WINDOWS	= "Shared.App.Data.Dir";
	
	public static final String	ENV_SHARED_DOC_ROOT_DIR				= "Shared_Doc_Dir";
	public static final String	ENV_SHARED_DOC_ROOT_DIR_ALT			= "SHARED_DOC_DIR";
	public static final String	ENV_SHARED_DOC_ROOT_DIR_WINDOWS		= "Shared.Doc.Dir";

	public static final String	ENV_USER_CONFIG_ROOT_DIR			= "User_App_Data_Dir";
	public static final String	ENV_USER_CONFIG_ROOT_DIR_ALT		= "USER_APP_DATA_DIR";
	public static final String	ENV_USER_CONFIG_ROOT_DIR_WINDOWS	= "User.App.Data.Dir";
	
	public static final String	ENV_USER_DOC_ROOT_DIR				= "User_Doc_Dir";
	public static final String	ENV_USER_DOC_ROOT_DIR_ALT			= "USER_DOC_DIR";
	public static final String	ENV_USER_DOC_ROOT_DIR_WINDOWS		= "User.Doc.Dir";

	public static final String[] ENV_SHARED_CONFIG_ROOT_DIR_ARRAY = {
		ENV_SHARED_CONFIG_ROOT_DIR, ENV_SHARED_CONFIG_ROOT_DIR_ALT, ENV_SHARED_CONFIG_ROOT_DIR_WINDOWS
	};

	public static final String[] ENV_SHARED_DOC_ROOT_DIR_ARRAY = {
		ENV_SHARED_DOC_ROOT_DIR, ENV_SHARED_DOC_ROOT_DIR_ALT, ENV_SHARED_DOC_ROOT_DIR_WINDOWS
	};

	public static final String[] ENV_USER_CONFIG_ROOT_DIR_ARRAY = {
		ENV_USER_CONFIG_ROOT_DIR, ENV_USER_CONFIG_ROOT_DIR_ALT, ENV_USER_CONFIG_ROOT_DIR_WINDOWS
	};

	public static final String[] ENV_USER_DOC_ROOT_DIR_ARRAY = {
		ENV_USER_DOC_ROOT_DIR, ENV_USER_DOC_ROOT_DIR_ALT, ENV_USER_DOC_ROOT_DIR_WINDOWS
	};

    public static final String	CONFIG_SUBDIRECTORY_ALL				= "All";
}
