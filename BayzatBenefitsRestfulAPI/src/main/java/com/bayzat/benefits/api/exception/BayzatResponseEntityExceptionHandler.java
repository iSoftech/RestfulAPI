package com.bayzat.benefits.api.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
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
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Exception Handler for all Exceptions through {@link Exception} class
	 * 
	 * @param exc
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception exc, WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(exc.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Exception Handler for Invalid Method Arguments through {@link MethodArgumentNotValidException} class
	 * 
	 * @param manvExc
	 * @param headers
	 * @param status
	 * @param request
	 * return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvExc,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("Invalid Method Argument: " + manvExc.getMessage(),
				manvExc.getBindingResult().getFieldError().toString(), new Date());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception Handler for Invalid Path Variables through {@link MissingPathVariableException} class
	 * 
	 * @param mpvExc
	 * @param headers
	 * @param status
	 * @param request
	 * return
	 */
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException mpvExc, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("Invalid Path Variable: " + mpvExc.getMessage(),
				"Expected Path Variable Name: " + mpvExc.getVariableName(), new Date());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}