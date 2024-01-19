package com.lab.sso.filter;

import com.lab.base.util.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Set;

@Component
@WebFilter( urlPatterns = "/**" )
@Order( Ordered.HIGHEST_PRECEDENCE )
public class AuthFilter implements Filter
{

	private final static Set<String> permittedPaths = Set.of( "/actuator", "/sso" );

	private final LoggerService loggerService;

	@Autowired
	public AuthFilter( LoggerService loggerService )
	{
		this.loggerService = loggerService;
	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
			ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		for( String permittedPath : permittedPaths )
		{
			if( httpRequest.getRequestURI().startsWith( permittedPath ) )
			{
				chain.doFilter( request, response );
				return;
			}
		}

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus( HttpServletResponse.SC_FORBIDDEN );
		httpResponse.getWriter().write( "Access Denied" );
		loggerService.accessDeny( this.getClass().getPackage(), httpRequest );
	}
}
