package ua.core.comp.os;

import ua.core.utils.SystemUtils;


public class OSFactory {

	// Singleton Static Parts:
	
	private volatile static OS instance;
	
	public static OS getInstance() {
		
		// Lazy Initialize
		// Double Checked Locking:
		
		if (instance == null) {
		
			synchronized (OS.class) {

				if (instance == null) {
					
					OS newInstance;
					
					if (SystemUtils.isOSWindows()) {
						newInstance = new OSWindows();
					}
					else if (SystemUtils.isOSOsx()) {
						newInstance = new OSMac();
					}
					else {
						newInstance = new OSLinux();
					}

					instance = newInstance;
				}
			}
		}
		
		return instance;
	}
}
