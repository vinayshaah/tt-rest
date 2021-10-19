package com.payment.pay.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String timestamp;
	private String message;
	private String details;

	public String getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public ServiceException(String timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ServiceException(String message) {
		super(message);
	}

	
}
