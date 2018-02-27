package ua.core.exceptions;

import java.util.List;

public class OperationTimedOut extends BaseException {

	private static final long serialVersionUID = 6322170300529366221L;

	public OperationTimedOut() {
		super();
	}

	public OperationTimedOut (String message) {
		super (message);
	}

	public OperationTimedOut (String message, Object... messageParamArray) {
		super (message, messageParamArray);
	}
	
	public OperationTimedOut (List <String> messageList) {
		super(messageList);
	}

}
