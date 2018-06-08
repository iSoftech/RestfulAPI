package com.bayzat.benefits.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mohamed Yusuff
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeResourceNotFoundException extends RuntimeException {
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = -7967884926222843986L;

	/**
	 * Parameterised Constructor to store Exception Message
	 * 
	 * @param exceptionMessage
	 */
	public EmployeeResourceNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
