/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.controller;

import javax.servlet.ServletContext;

public class AbstractAppInitializer implements IAppInitializer{
	protected ServletContext servletContext;
	
	@Override
	public void init() {
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("App Initialized");
			}
		});
		thread.start();
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
