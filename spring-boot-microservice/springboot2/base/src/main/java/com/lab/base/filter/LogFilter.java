package com.lab.base.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lab.base.util.LoggerService;

import ch.qos.logback.classic.Logger;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger( LogFilter.class );
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        // Log request details
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        String headers = request.getHeaderNames().toString(); // Adapt header logging as needed

        logger.info("Incoming request: uri={}, method={}, remoteAddr={}, headers={}", requestURI, method, remoteAddr, headers);

        // Proceed with the request
        filterChain.doFilter(request, response);

        // Log response details (optional)
        int status = response.getStatus();
        loggerService.info("Outgoing response: status={}", status);
	}

}
