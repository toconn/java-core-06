package ua.core.exceptions;

import java.util.List;

public class ItemNotFound extends BaseException {

	private static final long serialVersionUID = 1;

	public ItemNotFound() {
		super();
	}

	public ItemNotFound (String message) {
		super (message);
	}
	
	public ItemNotFound (String message, Object... messageParamArray) {
		super (message, messageParamArray);
	}
	
	public ItemNotFound (List <String> messageList) {
		super(messageList);
	}
}
