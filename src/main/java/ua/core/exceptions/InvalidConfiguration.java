package ua.core.exceptions;

import java.util.List;

public class InvalidConfiguration extends BaseException {

	private static final long serialVersionUID = 1;

	public InvalidConfiguration() {
		super();
	}

	public InvalidConfiguration (String message) {
		super (message);
	}

	public InvalidConfiguration (String message, Object... messageParamArray) {
		super (message, messageParamArray);
	}
	
	public InvalidConfiguration (List <String> messageList) {
		super(messageList);
	}
}
