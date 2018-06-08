/**
 * 
 */
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
 * @author Mohamed Yusuff
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

/*	*//**
	 * Exception Handler for {@link CompanyResourceNotFoundException} class
	 * 
	 * @param crnfExc
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
	@ExceptionHandler(CompanyResourceNotFoundException.class)
	public ResponseEntity<Object> handleCompanyNotFoundExceptions(CompanyResourceNotFoundException crnfExc,
			WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), crnfExc.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	*//**
	 * Exception Handler for {@link EmployeeResourceNotFoundException} class
	 * 
	 * @param ernfExc
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
	@ExceptionHandler(EmployeeResourceNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundExceptions(EmployeeResourceNotFoundException ernfExc,
			WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), ernfExc.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	*//**
	 * Exception Handler for {@link DependantResourceNotFoundException} class
	 * 
	 * @param drnfExc
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
	@ExceptionHandler(DependantResourceNotFoundException.class)
	public ResponseEntity<Object> handleDependantNotFoundExceptions(DependantResourceNotFoundException drnfExc,
			WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), drnfExc.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}*/

	/**
	 * Exception Handler for {@link BayzatTechnicalException} class
	 * 
	 * @param btExc
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BayzatTechnicalException.class)
	public ResponseEntity<Object> handleBayzatTechnicalExceptions(BayzatTechnicalException btExc, WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), btExc.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Exception Handler for {@link Exception} class
	 * 
	 * @param exc
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception exc, WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), exc.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Exception Handler for Invalid Method Arguments
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
		ErrorResponse errorResponse = new ErrorResponse(new Date(), "Invalid Method Arguments: " + manvExc.getMessage(),
				manvExc.getBindingResult().getFieldError().toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception Handler for Invalid Path Variables
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
		ErrorResponse errorResponse = new ErrorResponse(new Date(), "Invalid Path Variable: " + mpvExc.getMessage(),
				"Expected Path Variable Name: " + mpvExc.getVariableName());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}