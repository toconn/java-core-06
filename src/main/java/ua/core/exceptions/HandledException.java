package ua.core.exceptions;

import java.util.List;

public class HandledException extends BaseRuntimeException {

	private static final long serialVersionUID = 1;

	public HandledException(Exception originalException) {
		super(originalException);
	}

	public HandledException (String message, Exception originalException) {
		super (message, originalException);
	}
	
	public HandledException (String message, Object... messageParamArray) {
		super (message, messageParamArray);
	}
	
	public HandledException (List <String> messageList) {
		super(messageList);
	}
}
