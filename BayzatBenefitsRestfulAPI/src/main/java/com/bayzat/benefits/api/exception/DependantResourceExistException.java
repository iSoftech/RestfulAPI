package com.bayzat.benefits.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Dependant Resource Exist Runtime Exception Class
 * 
 * @author Mohamed Yusuff
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DependantResourceExistException extends RuntimeException {
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = 2788409016290846572L;

	/**
	 * Parameterised Constructor to store Exception Message
	 * 
	 * @param exceptionMessage
	 */
	public DependantResourceExistException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
