/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.vendormgmnt.vo.common;

public enum ComboOptionTable {
	PROJECT_LOCATION("master_project_location")
	;
	
	private ComboOptionTable(String tableName){
		this.tableName = tableName;
	}
	public final String tableName;
}
