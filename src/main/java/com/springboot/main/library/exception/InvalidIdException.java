package com.springboot.main.library.exception;

public class InvalidIdException extends Exception {

	private static final long serialVersionUID = 8636590495703142479L;

	private String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}