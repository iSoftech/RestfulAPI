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
	private String errorDetails;
	private Date errorOccurredTime;
	
	/**
	 * Argument Constructor to Instantiate with Error details
	 * 
	 * @param errorMessage
	 * @param errorDetails
	 * @param errorOccurredTime
	 */
	public ErrorResponse(String errorMessage, String errorDetails, Date errorOccurredTime) {
		super();
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
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
	public String getErrorDetails() {
		return errorDetails;
	}
	
	/**
	 * @return
	 */
	public Date getErrorOccurredTime() {
		return errorOccurredTime != null ? (Date) errorOccurredTime.clone() : null;
	}
}
