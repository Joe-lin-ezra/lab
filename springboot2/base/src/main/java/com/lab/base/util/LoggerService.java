package com.lab.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoggerService
{
	private final Logger logger = LoggerFactory.getLogger( LoggerService.class );

	public void accessDeny( HttpServletRequest request )
	{
		logger.info( request.getRemoteAddr() + " is denied to access the resource in " + request.getRequestURI() );
	}

	public void recordingQueryTime(Exception e)
	{
		logger.debug("");
	}

	public void exception( Exception e )
	{
		String exceptionSource = e.getStackTrace()[ 0 ].getClassName();
		String exceptionClass = e.getClass().getName();
		String exceptionReason = e.getMessage();

		logger.error( exceptionSource + " got " + exceptionClass + " by " + exceptionReason + ".\n", e );
	}
}
