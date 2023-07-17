package com.lab.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoggerService
{
	private final Logger logger = LoggerFactory.getLogger( LoggerService.class );

	public void accessDeny( Package source, HttpServletRequest request )
	{
		logger.info( "{0}: {1} is denied to access the resource in {2}.".formatted( source.getName(),
		                                                                            request.getRemoteAddr(), request.getRequestURI() ) );
	}

	public void recordingQueryTime( String methodName, long executionTime )
	{
		logger.info( "{0} took {1} milli-seconds.".formatted( methodName, executionTime ) );
	}

	public void exception( Exception e )
	{
		String exceptionSource = e.getStackTrace()[ 0 ].getClassName();
		String exceptionClass = e.getClass().getName();
		String exceptionReason = e.getMessage();

		logger.error( "{0}: {1} got {2} by {3}.\n".formatted( e.getStackTrace()[ 0 ].getClass().getPackage(),
		                                                      exceptionSource,
		                                                      exceptionClass,
		                                                      exceptionReason ), e );
	}
}
