/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.util;

public interface AppConfigConst {
	String REDIRECT_PREFIX = "redirect:";
	String USER_HOME_VIEW_PATH = REDIRECT_PREFIX + "/UserServlet/dashboard.html";
	
	String PAGE_MSG = "pageMsg";
	
	String PAGE_DATA = "pageData";
	
	String BODY_TITLE_KEY = "bodyTitle";
	
	String ACTION_SUFFIX = ".html";
	
	boolean IS_DEBUG_MODE = true;
	
	boolean IS_GOOGLE_AUTH_INTEGRATED = false;
	
	boolean IS_SHOW_CHAT_WINDOW = false;
	
	boolean IS_REM_ME_ENABLED = false;
	
	// TODO -- RAJIV -- Create new row in database to initialize GTEC information as preferred vendor
	long GTEC_VENDOR_ID = 100; // Vendor ID starts with 100
	
	String CURRENT_DATE = "currentDate";
}
