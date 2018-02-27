package ua.core.exceptions;

import java.util.List;

/**
 * Internal exception thrown at some point in the execution. The code
 * doesn't see any easy way to handle it, so wrap it and throw it as
 * a runtime exception.
 */
public class InternalException extends BaseRuntimeException {

	private static final long serialVersionUID = 1;

	public InternalException (Exception e) {
		super (e);
	}
	
	public InternalException (Exception e, String message) {
		super (e, message);
	}
	
	public InternalException (Exception e, String message, Object... messageParamArray) {
		super (e, message, messageParamArray);
	}
	
	public InternalException (Exception e, List <String> messageList) {
		super(e, messageList);
	}

}
