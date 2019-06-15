package org.curiousworks.lesson9;

public class Message {
	private String receiver;
	private String message;
	
	public Message(String receiver, String message) {
		this.receiver = receiver;
		this.message = message;
	}
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
