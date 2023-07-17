package com.lab.base.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DAOAspect
{
	@Before( value="execution(public * com.lab.*.dao.*.*(..))" )
	public void SQLPerformanceMonitor( JoinPoint joinPoint )
	{

	}

	@AfterReturning( value = "execution(public * com.lab.*.dao.*.*(..))", returning = "result" )
	public void afterReturningMethod( JoinPoint joinPoint, Object result )
	{

	}

	@AfterThrowing( value = "execution(public * com.lab.*.dao.*.*(..))", throwing = "exception" )
	public void afterThrowingMethod( JoinPoint joinPoint, Exception exception ) throws Exception
	{

	}
}
