/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter extends AbstractFilter{

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("Set NoCacheFilter for " + request.getRequestURI());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Server", "Sanlark Server");
		filterChain.doFilter(request, response);
	}
}
