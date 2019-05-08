/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractFilter implements Filter{
	
	@Override
	public void init(FilterConfig config){
		
	}
	
	@Override
	public final void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		handle(request, response, chain);
	}
	
	@Override
	public void destroy() {
		
	}
	
	protected abstract void handle(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;
}
