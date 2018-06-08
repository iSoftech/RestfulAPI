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

	private Date errorOccurredTime;
	private String errorMessage;
	private String errorDetails;
	
	/**
	 * Argument Constructor to Instantiate with Error details
	 * 
	 * @param errorOccurredTime
	 * @param errorMessage
	 * @param errorDetails
	 */
	public ErrorResponse(Date errorOccurredTime, String errorMessage, String errorDetails) {
		super();
		this.errorOccurredTime = errorOccurredTime;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}

	/**
	 * @return
	 */
	public Date getErrorOccurredTime() {
		return errorOccurredTime;
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
}
