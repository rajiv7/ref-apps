/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sanlark.lib.web.util.AppConfigConst;

@Controller
public class ActionUserServletController {
	
	@RequestMapping(value="UserServlet.html")
	public ModelAndView home(HttpServletRequest request){
		return getHomePage(request);
	}
	
	@RequestMapping(value="UserServlet/dashboard.html")
	public ModelAndView getHomePage(HttpServletRequest request){
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, "Dashboard");
		return new ModelAndView("body.page.common.dashboard");
	}
	
	@RequestMapping(value="UserServlet/manageCategory.html")
	public ModelAndView manageCategory(HttpServletRequest request){
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, "twoFieldsManage");
		return new ModelAndView("body.page.common.twoFieldsManage");
	}
}
