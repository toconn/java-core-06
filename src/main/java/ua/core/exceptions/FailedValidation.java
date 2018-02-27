package ua.core.exceptions;

import java.util.List;


public class FailedValidation extends BaseException {

	private static final long serialVersionUID = -1L;

	public FailedValidation() {
		super();
	}

	public FailedValidation (String message) {
		super (message);
	}
	
	public FailedValidation (String message, Object... messageParamArray) {
		super (message, messageParamArray);
	}
	
	public FailedValidation (List <String> messageList) {
		super(messageList);
	}

}
