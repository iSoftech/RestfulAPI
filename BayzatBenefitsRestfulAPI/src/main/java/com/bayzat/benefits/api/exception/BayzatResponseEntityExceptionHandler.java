package com.bayzat.benefits.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Bayzat Response Entity Exception Handler to handle Exceptions through @ControllerAdvice
 * 
 * @author Mohamed Yusuff
 */
@ControllerAdvice
@RestController
public class BayzatResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Exception Handler for Technical Exceptions through {@link BayzatTechnicalException} class
	 * 
	 * @param btExc
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BayzatTechnicalException.class)
	public ResponseEntity<Object> handleBayzatTechnicalExceptions(BayzatTechnicalException btExc, WebRequest request)
			throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(btExc.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(errorResponse,
				btExc.getHttpStatus() != null ? btExc.getHttpStatus() : HttpStatus.NOT_FOUND);
	}
}