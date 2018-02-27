package ua.core.utils;

/**
 * A simple class to say when it has passed a particular time.
 */
public class TimeEnd {
	
	private long systemEndTimeMS;

	public TimeEnd (long systemEndTimeMS) {
		this.systemEndTimeMS = systemEndTimeMS;
	}
	
	public boolean hasEnded() {
		return this.systemEndTimeMS <= System.currentTimeMillis();
	}
	
	public boolean hasNotEnded() {
		return ! hasEnded();
	}

}
