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
public class BayzatTechnicalException extends RuntimeException {
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = 6853858869431791704L;

	/**
	 * Parameterised Constructor to store Error Message
	 * 
	 * @param errorMessage
	 */
	public BayzatTechnicalException(String errorMessage) {
		super(errorMessage);
	}
}
