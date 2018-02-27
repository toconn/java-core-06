package ua.core.exceptions;

import java.util.List;

public class RemoteServerException extends BaseRuntimeException {

	private static final long serialVersionUID = -1;

	public RemoteServerException (Exception e) {
		super (e);
	}

	public RemoteServerException (Exception e, String message) {
		super (e, message);
	}
	
	public RemoteServerException (Exception e, List <String> messageList) {
		super (e, messageList);
	}
	
	public RemoteServerException (String message) {
		super (message);
	}
	
	public RemoteServerException (List <String> messageList) {
		super (messageList);
	}
}
