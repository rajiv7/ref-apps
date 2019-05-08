/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.common;

public enum InhousePersonStatus {
	ACTIVE(0, "Active"),
	INACTIVE(1, "Inactive")
	;
	
	private InhousePersonStatus(int id, String title){
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
}
