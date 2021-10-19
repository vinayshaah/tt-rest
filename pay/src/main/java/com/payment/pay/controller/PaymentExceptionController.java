package com.payment.pay.controller;

import java.util.Date;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.payment.pay.exception.InvalidAmountException;
import com.payment.pay.exception.ServiceException;

@RestController
@ControllerAdvice
public class PaymentExceptionController extends ResponseEntityExceptionHandler  {

	
	  @ExceptionHandler(InvalidAmountException.class) 
	  public ResponseEntity<Object> handleInvalidAmountEx(InvalidAmountException ex, WebRequest request) { 
		  //creating exception response structure  
		  ServiceException exceptionResponse= new ServiceException(String.valueOf(new Date()) , 
				  ex.getMessage(), request.getDescription(false));  
		  //returning exception structure and Not Found status   
		  return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
	 
	
		@ExceptionHandler(Exception.class)  
		public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
			//creating exception response structure  
			ServiceException exceptionResponse= new ServiceException(
					String.valueOf(new Date()), ex.getMessage(), request.getDescription(false));  
			//returning exception structure and specific status   
			return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);  
		}
	
}
