/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.controller.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.FormLogin;
import com.sanlark.lib.web.pojo.PageMsg;
import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.ResponseUtil;
import com.sanlark.lib.web.util.SessionUtil;
import com.sanlark.vendormgmnt.logicLayer.IAuthService;

@Controller
public class ActionAuthSecurity {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IAuthService authService;
	
	private static final String LOGIN_VIEW = "common.access.login";
	
	@RequestMapping(value="login.html")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response){
		return loginPageRequest(request, response);
	}

	@RequestMapping(value="Login.html", method=RequestMethod.GET)
	public ModelAndView loginPageRequest(HttpServletRequest request, HttpServletResponse response) {
		if(SessionUtil.isActiveSession(request.getSession())){
			return getDashboardView();
		}
		PageMsg pageMsg = ResponseUtil.gePageMsg(request);
		if(pageMsg == null){
			pageMsg = new PageMsg();
		}
		if(CommonUtil.isEmpty(pageMsg.getMsg())){
			pageMsg.setSuccessMsg("Sign in to start your session");
		}
		pageMsg.setElement("loginId");
		request.setAttribute(AppConfigConst.PAGE_MSG, pageMsg);
		request.setAttribute(AppConfigConst.PAGE_DATA, new FormLogin());
		return new ModelAndView(LOGIN_VIEW);
	}
	
	@RequestMapping(value="Login.html", method=RequestMethod.POST)
	public ModelAndView doPost(@ModelAttribute("FormLogin") FormLogin form, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, Locale locale){
		
		PageMsg pageMsg = validateCredentials(request, response, form);
		if(pageMsg.isError() == false){
			if(CommonUtil.isEmpty(form.getRedirectPath())){
				return getDashboardView();
			}
			return new ModelAndView(AppConfigConst.REDIRECT_PREFIX + form.getRedirectPath());
		}
		request.setAttribute("pageData", form);
		request.setAttribute("redirectPath", form.getRedirectPath());
		
		ResponseUtil.setPageMsg(request, pageMsg, messageSource, locale);
		return new ModelAndView(LOGIN_VIEW);
	}
	
	private PageMsg validateCredentials(HttpServletRequest request, 
			HttpServletResponse response, FormLogin form){
		PageMsg pageMsg = form.validate();
		if(pageMsg.isError()){
			return pageMsg;
		}
		AccessTicket accessTicket = authService.validate(form.getLoginId(), form.getPasswd());
		if(accessTicket == null){
			pageMsg.setError("validation.invalid.credentials");
			return pageMsg;
		}
		SessionUtil.activate(request.getSession(), accessTicket);
		return pageMsg;
	}
	
	private ModelAndView getDashboardView(){
		return new ModelAndView(AppConfigConst.USER_HOME_VIEW_PATH);
	}
	
	@RequestMapping(value="logout.html")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		if(SessionUtil.deactivate(request.getSession())){
			ResponseUtil.setOneTimeMsg(request, "You are logged out now", false);
		}
		return new ModelAndView("redirect:/login.html");
	}
}
