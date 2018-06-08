package com.bayzat.benefits.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mohamed Yusuff
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DependantResourceNotFoundException extends RuntimeException {
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = 2788409016290846572L;

	/**
	 * Parameterised Constructor to store Exception Message
	 * 
	 * @param exceptionMessage
	 */
	public DependantResourceNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
