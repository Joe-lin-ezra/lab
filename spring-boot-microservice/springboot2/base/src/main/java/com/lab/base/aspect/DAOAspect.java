package com.lab.base.aspect;

import com.lab.base.util.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DAOAspect
{

	private LoggerService loggerService;

	public DAOAspect( LoggerService loggerService )
	{
		this.loggerService = loggerService;
	}

	@Around( "execution(public * com.lab.*.dao.*.*(..))" )
	public Object measureExecutionTime( ProceedingJoinPoint joinPoint ) throws Throwable
	{
		String targetMethodName = joinPoint.getTarget().getClass().getName();
		Object result = null;
		long startTime = System.currentTimeMillis();

		try
		{
			result = joinPoint.proceed();
		} catch( Throwable throwable )
		{
			long executionTime = System.currentTimeMillis() - startTime;
			loggerService.recordingQueryTime( joinPoint.getTarget().getClass().getName(), executionTime );
			throw throwable;
		}

		long executionTime = System.currentTimeMillis() - startTime;
		loggerService.recordingQueryTime( joinPoint.getTarget().getClass().getName(), executionTime );
		return result;
	}
}
