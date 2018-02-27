package ua.core.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Measures the time between start and stop events.
 */
public class Timer {
	
	// Constants ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final DecimalFormat OPERATION_PER_SECOND_FORMAT	= new DecimalFormat ("#,###,###,###,###,##0.0");

	
	private enum TimerStatus {
		
		None,
		Started,
		Stopped
	}
	
	// Properties ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private long		startTime	= 0;
	private long		stopTime	= 0;
	private TimerStatus	status		= TimerStatus.None;


	// Main ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Timer () {
		
		start ();
	}
	
	
	public void start () {

		this.startTime	= System.currentTimeMillis ();
		this.stopTime	= 0;
		
		this.status		= TimerStatus.Started;
	}

	public void stop () {

		this.stopTime	= System.currentTimeMillis ();
		this.status		= TimerStatus.Stopped;
	}

	/**
	 * Returns the elapsed time in milliseconds.
	 * 
	 * @return
	 */
	public long getElapsedTimeMilliSec () {

		// DECLARATIONS:

		long elapsed;
		
		
		// CODE:

		switch (this.status) {
			
			case Started:
			
				elapsed = (System.currentTimeMillis () - startTime);
				break;
				
			case Stopped:
				
				elapsed = (stopTime - startTime);
				break;
	
			default:
				
				elapsed = 0;
				break;
		}
		
			
		return elapsed;
	}


	/**
	 * Returns the elapsed time in seconds.
	 * 
	 * @return
	 */
	public float getElapsedTimeSec () {

		float elapsedTimeSec;
		
		elapsedTimeSec = (getElapsedTimeMilliSec() / 1000f);

		return elapsedTimeSec;
	}
	
	
	/**
	 * Returns the number of transactions a second.
	 * 
	 * @param transactionCount
	 * @return
	 */
	public float getOperationsPerSec (int operationCount) {
		
		float elapsedTimeSec;
		
		elapsedTimeSec = getElapsedTimeSec ();
		
		if (elapsedTimeSec != 0) {
			
			return operationCount / elapsedTimeSec;
		}
		else {
		
			return -1;
		}
	}

	
	/**
	 * Returns the number of transactions a second.
	 * 
	 * @param transactionCount
	 * @return
	 */
	public String getOperationsPerSecFormatted (int operationCount) {
		
		float elapsedTimeSec;
		
		elapsedTimeSec = getOperationsPerSec (operationCount);
		
		if (elapsedTimeSec != -1) {
			
			return OPERATION_PER_SECOND_FORMAT.format (elapsedTimeSec);
		}
		else {
		
			return "?";
		}
	}

	
	public String toString () {
		
		// DECLARATION:
		
		NumberFormat		numberFormat;
		
			
		// CODE:

		numberFormat		= new DecimalFormat ("#0.000s");
		return numberFormat.format (getElapsedTimeSec ());

	}
}
