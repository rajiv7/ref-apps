/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 03-Jul-2015 
 */
package com.sanlark.lib.web.util;

import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;

public class HtmlUtil {
	public static String getRequestStatusClass(String projectStatus){
		String out = "";
		for(RequestStatus status : RequestStatus.values()){
			if(status.title.equals(projectStatus)){
				out = status.uiClass;
				break;
			}
		}
		return out;
	}
}
