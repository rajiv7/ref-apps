/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.logicLayer.impl;

import static com.sanlark.lib.web.pojo.UserRole.*;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.UserRole;
import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.LdapValidator;
import com.sanlark.vendormgmnt.datalayer.IUserDao;
import com.sanlark.vendormgmnt.logicLayer.IAuthService;

public class AuthServiceImpl implements IAuthService{
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public AccessTicket validate(String loginId, String passwd) {
		AccessTicket accessTicket = getTicket(loginId, passwd);
		if(accessTicket != null){
			// Validate into user_info_table
			userDao.update(accessTicket);
		}
		return accessTicket;
	}

	private AccessTicket getTicket(String loginId, String passwd) {
		AccessTicket accessTicket = null;
		if(AppConfigConst.IS_DEBUG_MODE){
			accessTicket = accessTicketCol.get(loginId);
		}
		
		if(accessTicket == null){
			if(AppConfigConst.IS_GOOGLE_AUTH_INTEGRATED){
				// TODO -- Validate credential from Google
			}else{
				accessTicket = LdapValidator.getInstance().get(loginId, passwd);
			}
		}
		
		return accessTicket;
	}
	
	private static Map<String, AccessTicket> accessTicketCol;
	
	static{
		accessTicketCol = new Hashtable<String, AccessTicket>();
		
		put("gtec", "Kumar", "rajiv@sanlark.com", GTECC);
		put("proc", "Ranjan", "rajiv@sanlark.com", PROCUREMENT);
		
		put("kumar28", "Rajiv Kumar", "rajiv@sanlark.com", REQUESTER);
	}
	
	private static void put(String ldapId, String name, String email, UserRole userRole){
		AccessTicket obj = new AccessTicket();
		obj.setLoginId(ldapId);
		obj.setName(name);
		obj.setEmail(email);
		obj.setUserRole(userRole);
		accessTicketCol.put(ldapId, obj);
	}
}
