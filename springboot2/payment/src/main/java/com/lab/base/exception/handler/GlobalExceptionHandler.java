package com.lab.base.exception.handler;

import com.lab.base.dto.exception.MethodArgumentErrorResponseDTO;
import com.lab.base.dto.response.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler
{
	private Environment environment;

	@Autowired
	public GlobalExceptionHandler( Environment environment )
	{
		this.environment = environment;
	}

	@ExceptionHandler( MethodArgumentNotValidException.class )
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<MethodArgumentErrorResponseDTO> handleValidationException( MethodArgumentNotValidException ex )
	{
		if ("prod".equals( environment.getProperty( "SPRING_BOOT_ENV" ) ))
		{
			return ResponseEntity.badRequest().build();
		}

		BindingResult bindingResult = ex.getBindingResult();

		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ValidationErrorDTO> validationErrors = new ArrayList<>();

		for( FieldError fieldError : fieldErrors )
		{
			String fieldName = fieldError.getField();
			Object rejectedValue = fieldError.getRejectedValue();
			String defaultMessage = fieldError.getDefaultMessage();

			ValidationErrorDTO error = new ValidationErrorDTO( fieldName, rejectedValue, defaultMessage );
			validationErrors.add( error );
		}

		MethodArgumentErrorResponseDTO errorResponse =
				new MethodArgumentErrorResponseDTO( HttpStatus.BAD_REQUEST, "Validation failed", validationErrors );
		return ResponseEntity.badRequest().body( errorResponse );
	}

	@ExceptionHandler( Exception.class)
	@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllException( MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().build();
	}
}
