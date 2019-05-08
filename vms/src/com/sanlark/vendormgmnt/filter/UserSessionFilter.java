/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.controller.AbstractFilter;
import com.sanlark.lib.web.util.ResponseUtil;
import com.sanlark.lib.web.util.SessionUtil;
import com.sanlark.lib.web.util.WebUtil;

public class UserSessionFilter extends AbstractFilter{
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		if(SessionUtil.isActiveSession(request.getSession())){
			chain.doFilter(request, response);
		}else{
			if("POST".equalsIgnoreCase(request.getMethod())){
				String ajaxCall = CommonUtil.trim(request.getParameter("ajaxCall"));
				if("true".equals(ajaxCall)){
					String dataType = CommonUtil.trim(request.getParameter("dataType"), "html");
					boolean isJsonRequest =  "json".equals(dataType);
					ResponseUtil.serializeTimeout(request, response, isJsonRequest);
					return;
				}
			}
			
			String redirectPath = request.getRequestURI();

			redirectPath = redirectPath.replaceFirst(WebUtil.getContextPath(request), "/");
			
		    if (request.getQueryString() != null){
		    	 redirectPath += "?" + request.getQueryString();
		    }
		    
			request.setAttribute("redirectPath", redirectPath);
			ResponseUtil.forward(request, response, "/login.html");
		}
	}
}
