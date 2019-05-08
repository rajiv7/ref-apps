/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.sanlark.lib.web.pojo.AccessTicket;

public final class LdapValidator {

	private static final String FILE_PATH = "LdapConfig.properties";
	
	private static LdapValidator _instance;
	
	public static synchronized LdapValidator getInstance(){
		if(_instance == null){
			_instance = new LdapValidator();
		}
		return _instance;
	}

	private Authenticate authenticator;
	private List<String> siteAdminCol;
	
	private LdapValidator(){
		authenticator = new Authenticate(FILE_PATH);
		siteAdminCol = new ArrayList<String>();
		siteAdminCol.add("kumar28");
	}
	
	@SuppressWarnings("rawtypes")
	public Hashtable getInfo(String ldapId, String passwd){
		Hashtable hashTable = null;
        try {
			hashTable = authenticator.ldapAuthenticate(ldapId, passwd);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return hashTable;
	}
	
	public boolean isValid(String ldapId, String passwd){
		@SuppressWarnings("rawtypes")
		Hashtable hashTable = getInfo(ldapId, passwd);
        return (hashTable != null);
	}

	public AccessTicket get(String ldapId, String passwd) {
		@SuppressWarnings("rawtypes")
		Hashtable hashTable = getInfo(ldapId, passwd);
		if(hashTable == null){
			return null;
		}
		AccessTicket accessTicket = new AccessTicket();
		accessTicket.setLoginId(ldapId);
		accessTicket.setName("" + hashTable.get("givenname"));
		accessTicket.setEmail("" + hashTable.get("mail"));
		return accessTicket;
	}
}
