/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 14-Jul-2015 
 */

package com.sanlark.vendormgmnt.logicLayer;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.UserRole;

public class UserRoleUtil {
	public static boolean isSiteAdmin(AccessTicket accessTicket){
		UserRole userRole = accessTicket.getUserRole();
		if(userRole == UserRole.SITE_ADMIN){
			return true;
		}
		return false;
	}
	
	public static boolean isGtec(AccessTicket accessTicket){
		UserRole userRole = accessTicket.getUserRole();
		if(userRole == UserRole.SITE_ADMIN
				|| userRole == UserRole.GTECC){
			return true;
		}
		return false;
	}
	
	public static boolean isProcumentor(AccessTicket accessTicket){
		UserRole userRole = accessTicket.getUserRole();
		if(userRole == UserRole.SITE_ADMIN
				|| userRole == UserRole.PROCUREMENT){
			return true;
		}
		return false;
	}
}
