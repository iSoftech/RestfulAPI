/**
 * 
 */
package com.bayzat.benefits.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Bayzat Technical Exception Class extended with Runtime Exception Class
 * 
 * @author Mohamed Yusuff
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BayzatTechnicalException extends RuntimeException {
	
	private HttpStatus httpStatus;
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = 6853858869431791704L;

	/**
	 * Parameterised Constructor to store Error Message
	 * 
	 * @param errorMessage
	 * @param httpStatus
	 */
	public BayzatTechnicalException(String errorMessage, HttpStatus httpStatus) {
		super(errorMessage);
		this.httpStatus = httpStatus;
	}

	/**
	 * @return
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
