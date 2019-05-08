/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.vendormgmnt.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.ResponseUtil;

@Controller
public class ActionManageCommonDataTables {
	
	@RequestMapping(value="/DataTables.html")
	public ModelAndView invalidAccess() {
		return new ModelAndView(AppConfigConst.USER_HOME_VIEW_PATH);
	}
	
	@RequestMapping(value="/DataTables/{urlPath}.html", method=RequestMethod.GET)
	public ModelAndView doGet() {
		return new ModelAndView("common.access.forgetPasswd");
	}
	
	@RequestMapping(value="/DataTables/{urlPath}.html", method=RequestMethod.POST)
	public void test(HttpServletRequest request, HttpServletResponse response){
		System.out.println(request.getContentType());
		ResponseUtil.serialize(response, "Login Failed");
	}
}
