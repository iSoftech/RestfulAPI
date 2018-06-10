/**
 * 
 */
package com.bayzat.benefits.api.exception;

import java.util.Date;

/**
 * Error Response Class to store Error Details
 * 
 * @author Mohamed Yusuff
 */
public class ErrorResponse {

	private String errorMessage;
	private String errorLocation;
	private Date errorOccurredTime;
	
	/**
	 * Argument Constructor to Instantiate with Error details
	 * 
	 * @param errorMessage
	 * @param errorLocation
	 * @param errorOccurredTime
	 */
	public ErrorResponse(String errorMessage, String errorLocation, Date errorOccurredTime) {
		super();
		this.errorMessage = errorMessage;
		this.errorLocation = errorLocation;
		this.errorOccurredTime = errorOccurredTime;
	}
	
	/**
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return
	 */
	public String getErrorLocation() {
		return errorLocation;
	}
	
	/**
	 * @return
	 */
	public Date getErrorOccurredTime() {
		return errorOccurredTime != null ? (Date) errorOccurredTime.clone() : null;
	}
}
