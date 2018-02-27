package ua.core.exceptions;

import java.util.List;


public class InvalidRequest extends BaseException {


	private static final long serialVersionUID = -6216136454678273600L;

	
	public InvalidRequest() {

		super();
	}

	public InvalidRequest (String message) {

		super (message);
	}
	
	public InvalidRequest (List <String> messageList) {

		super(messageList);
	}
}
