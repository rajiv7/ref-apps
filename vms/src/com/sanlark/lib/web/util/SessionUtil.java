/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sanlark.lib.web.pojo.AccessTicket;

public class SessionUtil {
	
	public static final String SESSION_KEY = "accessTicket";
	public static final int MAX_INACTIVE_SEC = 900;
	
	public static void activate(HttpSession session, AccessTicket accessTicket){
		set(session, SESSION_KEY, accessTicket);
		session.setMaxInactiveInterval(MAX_INACTIVE_SEC);
	}
	
	public static boolean isActiveSession(HttpSession session){
		return (getTicket(session) != null);
	}

	public static boolean deactivate(HttpSession session) {
		if(isActiveSession(session)){
			session.removeAttribute(SESSION_KEY);
			return true;
		}
		return false;
	}
	
	public static AccessTicket getTicket(HttpServletRequest request) {
		return getTicket(request.getSession());
	}

	public static AccessTicket getTicket(HttpSession session) {
		return get(session, SESSION_KEY);
	}

	public static void set(HttpServletRequest request, String key, Object value){
		set(request.getSession(), key, value);
	}
	
	public static void set(HttpSession session, String key, Object value){
		session.setAttribute(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(HttpSession session, String key){
		return (T)session.getAttribute(key);
	}
}
