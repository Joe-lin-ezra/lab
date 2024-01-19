package com.lab.base.advice;

import com.lab.base.dto.exception.ValidationErrorDTO;
import com.lab.base.util.LoggerService;
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

	private LoggerService loggerService;

	@Autowired
	public GlobalExceptionHandler( Environment environment, LoggerService loggerService )
	{
		this.environment = environment;
		this.loggerService = loggerService;
	}

	@ExceptionHandler( MethodArgumentNotValidException.class )
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<Object> handleValidationException( MethodArgumentNotValidException ex )
	{
		if( "prod".equals( environment.getProperty( "SPRING_BOOT_ENV" ) ) )
		{
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
		}

		BindingResult bindingResult = ex.getBindingResult();

		List<ValidationErrorDTO> validationErrors = new ArrayList<>();
		for( FieldError fieldError : bindingResult.getFieldErrors() )
		{
			String fieldName = fieldError.getField();
			Object rejectedValue = fieldError.getRejectedValue();
			String defaultMessage = fieldError.getDefaultMessage();

			ValidationErrorDTO error = new ValidationErrorDTO( fieldName, rejectedValue, defaultMessage );
			validationErrors.add( error );
		}

		return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( validationErrors );
	}

	@ExceptionHandler( Exception.class )
	@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
	public ResponseEntity<Object> handleAllException( Exception e )
	{
		loggerService.exception( e );
		return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
	}
}
