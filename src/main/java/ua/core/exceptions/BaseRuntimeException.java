package ua.core.exceptions;

import java.util.ArrayList;
import java.util.List;

import ua.core.utils.CollectionUtils;
import ua.core.utils.MessageUtils;



public abstract class BaseRuntimeException extends RuntimeException {

	private static final long	serialVersionUID = -3138741202946024745L;

	private List<String> messages = new ArrayList<>();

	
	public BaseRuntimeException() {

		super();
	}
	
	public BaseRuntimeException (Exception e) {
		
		super (e);
		messages.add (e.getMessage());
	}

	public BaseRuntimeException (Exception e, String message) {
		super(e);
		messages.add (message);
	}

	public BaseRuntimeException (Exception e, List<String> messages) {
		
		super(e);

		if (CollectionUtils.isNotEmpty (messages)) {
			messages.add (messages.get (0));
		}
		
		this.messages = messages;
	}

	public BaseRuntimeException (Exception e, String message, Object... messageParamArray) {

		super(e);
		addMessage (message, messageParamArray);
	}

	public BaseRuntimeException (String message) {

		super();
		messages.add (message);
	}

	public BaseRuntimeException (String message, Object... messageParamArray) {

		super();
		addMessage (message, messageParamArray);
	}

	public BaseRuntimeException (List <String> messageList) {

		super();
		this.messages = messageList;
	}

	
	public void addFirstMessage (String message) {

		messages.add (0, message);
	}
	
	public void addFirstMessage (String message, Object... messageParamArray) {
		
		messages.add (0, MessageUtils.toString(message, messageParamArray));
	}

	public void addMessage (String message) {

		messages.add (message);
	}

	public void addMessage (String message, Object... messageParamArray) {
		
		messages.add (MessageUtils.toString(message, messageParamArray));
	}

	public void addMessageList (List <String> messageList) {

		this.messages.addAll (messageList);
	}

	public List <String> getMessageList() {

		return messages;
	}
}
