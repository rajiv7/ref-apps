/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.vendormgmnt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanlark.lib.web.controller.AbstractFilter;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.util.ResponseUtil;
import com.sanlark.lib.web.util.SessionUtil;
import com.sanlark.vendormgmnt.logicLayer.UserRoleUtil;

public class ContentEditAccessFilter extends AbstractFilter{

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		AccessTicket accessTicket = SessionUtil.getTicket(request.getSession());
		if(UserRoleUtil.isProcumentor(accessTicket)){
			chain.doFilter(request, response);
		}else{
			ResponseUtil.redirect(request, response, "UserServlet.html", "Access Denied", true);
		}
	}
}
