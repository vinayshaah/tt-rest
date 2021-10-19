package com.payment.pay.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 

//@ResponseStatus(HttpStatus.BAD_REQUEST)  
public class InvalidAmountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1910497801791042080L;

	public InvalidAmountException(String message) {
		super(message);
	}

	
}
