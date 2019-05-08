/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.util;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;

import com.google.gson.Gson;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.JsonResponse;
import com.sanlark.lib.web.pojo.PageMsg;
import com.sanlark.lib.web.pojo.ResponseCode;

public class ResponseUtil {
	
	public static void serialize(HttpServletResponse response, Object data){
		JsonResponse json = new JsonResponse();
		json.setData(data);
		json.setStatusId(ResponseCode.SUCCESS.ordinal());
		serialize(response, json);
	}
	
	public static void serialize(HttpServletResponse response, String msg, ResponseCode responseCode){
		if(responseCode == null){
			responseCode = ResponseCode.FAILED;
		}
		JsonResponse json = new JsonResponse();
		json.setData(msg);
		json.setError(responseCode.isError);
		json.setStatusId(responseCode.id);
		serialize(response, json);
	}
	
	public static void serialize(HttpServletResponse response, JsonResponse json){
		response.setContentType("application/json");
		try {
			response.getOutputStream().print(new Gson().toJson(json));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendErrorHtml(HttpServletResponse response, ResponseCode code){
		sendErrorHtml(response, null, code);
	}
	
	public static void sendErrorHtml(HttpServletResponse response, String errorMessage, ResponseCode code){
		errorMessage = CommonUtil.trim(errorMessage);
		if(code == ResponseCode.TIMEOUT
				|| code == ResponseCode.REDIRECT){
			String htmlTxt = "error:" + code.id + errorMessage;
			sendHtml(response, htmlTxt);
		}
	}
	
	public static void sendHtml(HttpServletResponse response, String htmlTxt){
		response.setContentType("text/html");
		try {
			response.getOutputStream().print(htmlTxt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String forwardPath){
		try{
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
			rd.forward(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String redirectPath){
		try {
			response.sendRedirect(WebUtil.getContextPath(request) + redirectPath);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, 
			String redirectPath, String oneTimeMsg){
			redirect(request, response, redirectPath, oneTimeMsg, false);
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, 
			String redirectPath, String oneTimeMsg, boolean isErrorMsg){
		setOneTimeMsg(request, oneTimeMsg, isErrorMsg);
		redirect(request, response, redirectPath);
	}
	
	public static String getMsg(MessageSource messageSource, Locale locale, String msgKey){
		String msg = messageSource.getMessage(msgKey, null, locale);
		return msg == null ? "" : msg;
	}
	
	public static void setPageMsg(HttpServletRequest request, PageMsg pageMsg, 
			MessageSource messageSource, Locale locale){
		String errorMsg = getMsg(messageSource, locale, pageMsg.getMsg());
		pageMsg.setMsg(errorMsg);
		request.setAttribute(AppConfigConst.PAGE_MSG, pageMsg);
	}
	
	public static void setOneTimeMsg(HttpServletRequest request,
			String msgKey, MessageSource messageSource, Locale locale) {
		setOneTimeMsg(request, msgKey, false, messageSource, locale);
	}

	public static void setOneTimeMsg(HttpServletRequest request,
			String msgKey, boolean isError, MessageSource messageSource,
			Locale locale) {
		String msg = getMsg(messageSource, locale, msgKey);
		setOneTimeMsg(request, msg, isError);
	}
	
	public static PageMsg gePageMsg(HttpServletRequest request){
		HttpSession  session = request.getSession();
		PageMsg pageMsg = (PageMsg)request.getAttribute(AppConfigConst.PAGE_MSG);
		if(pageMsg == null){
			pageMsg = SessionUtil.get(request.getSession(), AppConfigConst.PAGE_MSG);
			if(pageMsg != null){
				session.removeAttribute(AppConfigConst.PAGE_MSG);
			}
		}
		
		if(pageMsg == null){
			pageMsg = new PageMsg();
		}
		return pageMsg;
	}

	/**
	 * @param request
	 * @param string
	 * @param b
	 */
	public static void setOneTimeMsg(HttpServletRequest request, String msg,
			boolean isError) {
		PageMsg pageMsg = new PageMsg();
		
		if(isError){
			pageMsg.setError(msg);
		}else{
			pageMsg.setSuccessMsg(msg);
		}
		setOneTimeMsg(request, pageMsg);
	}
	public static void setOneTimeMsg(HttpServletRequest request, PageMsg pageMsg){
		request.getSession().setAttribute(AppConfigConst.PAGE_MSG, pageMsg);
	}

	public static void serializeTimeout(HttpServletRequest request, 
			HttpServletResponse response, boolean isJsonRequest) {
		if(isJsonRequest){
			serialize(response, "Session Timeout", ResponseCode.TIMEOUT);
		}else{
			ResponseUtil.setOneTimeMsg(request, "Session Timeout", true);
			sendErrorHtml(response, ResponseCode.TIMEOUT);
		}
	}
}
