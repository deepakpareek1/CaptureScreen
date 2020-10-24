package com.ajackus.CaptureScreen.Controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * CORSFilter is Cross Origin Resource sharing filter which filters the request based on domain names, header parameters etc..
 * @author HTC Global Services
 *
 */
public class CORSFilter implements Filter {

	@Override
	public void destroy() {
		/*
		 * This is used to destroy
		 */
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
	        HttpServletResponse response = (HttpServletResponse) res;
	        
	        response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Max-Age", "360");
	        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With, remember-me,Content-Disposition");
	        response.setHeader("Access-Control-Request-Headers", "Content-Type, Accept, X-Requested-With");
	        chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		/*
		 * This is used to init
		 */
	}
}
