package ua.core.exceptions;

import java.util.List;

public class InvalidValue extends BaseException {

	private static final long serialVersionUID = 6322170300529366221L;

	public InvalidValue() {
		super();
	}

	public InvalidValue (String message) {
		super (message);
	}

	public InvalidValue (String message, Object... messageParamArray) {
		super (message, messageParamArray);
	}
	
	public InvalidValue (List <String> messageList) {
		super(messageList);
	}
}
