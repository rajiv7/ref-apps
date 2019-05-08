/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.lib.web.pojo;

public enum UserRole implements IEnumComboLoader{
	SITE_ADMIN (1, "Site Admin"),
	GTECC(2, "GTEC"),
	PROCUREMENT(3, "Procurement"),
	REQUESTER(4, "Requester")
	;
	
	private UserRole(int id, String title){
		this.id = id;
		this.title = title;
	}
	
	public final int id;
	public final String title;
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	/**
	 * @param userRoleId
	 * @return
	 */
	public static UserRole get(long userRoleId) {
		UserRole userRole = REQUESTER;
		for(UserRole r : values()){
			if(r.id == userRoleId){
				userRole = r;
				break;
			}
		}
		return userRole;
	}
}
