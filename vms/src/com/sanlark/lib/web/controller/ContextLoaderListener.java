/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.sanlark.lib.core.util.CommonUtil;

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		
		String initlizerClass = context.getInitParameter("AppInitImpl");
		AbstractAppInitializer initlizer = CommonUtil.load(initlizerClass);
		if(initlizer != null){
			initlizer.setServletContext(context);
			initlizer.init();
		}
	}
}
