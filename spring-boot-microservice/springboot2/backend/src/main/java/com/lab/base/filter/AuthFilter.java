package com.lab.base.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order( Ordered.HIGHEST_PRECEDENCE )
public class AuthFilter implements Filter
{

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
			ServletException
	{
		chain.doFilter( request, response );
		return;
	}
}
