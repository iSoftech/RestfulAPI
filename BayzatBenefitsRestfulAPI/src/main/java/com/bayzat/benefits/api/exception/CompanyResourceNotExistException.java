/**
 * 
 */
package com.bayzat.benefits.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mohamed Yusuff
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyResourceNotExistException extends RuntimeException {
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = -7206259820819733712L;

	/**
	 * Parameterised Constructor to store Exception Message
	 * 
	 * @param exceptionMessage
	 */
	public CompanyResourceNotExistException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
