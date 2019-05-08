/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import com.sanlark.lib.core.datalayer.IMappingTable;

public enum BatchTableMapping implements IMappingTable{
	SERVICE_CATEGORY("master_service_category", "serviceCategory", "Manage Service Category"),
	SERVICE_LOCATION("master_service_location", "serviceLocation", "Manage Service Location"),
	SERVICE_SKILL("master_service_skill", "skill", "Manage Skills"),
	DESIGNATION("master_designation", "designation", "Manage Designation"),
	;
	
	private BatchTableMapping(String tableName, String urlPath, String bodyTitle){
		this.tableName = tableName;
		this.keyClmName = "record_id";
		this.valueClmName = "title";
		this.urlPath = urlPath;
		this.bodyTitle = bodyTitle;
	}
	
	public final String tableName;
	public final String keyClmName;
	public final String valueClmName;
	public final String urlPath;
	public final String bodyTitle;
	
	@Override
	public String getTableName() {
		return tableName;
	}
	@Override
	public String getKeyClmName() {
		return keyClmName;
	}
	@Override
	public String getValueClmName() {
		return valueClmName;
	}
	@Override
	public boolean isFilterInactive() {
		return true;
	}
	
	public static BatchTableMapping get(String urlPath) {
		BatchTableMapping tableMapping = null;
		for(BatchTableMapping table : values()){
			if(table.urlPath.equals(urlPath)){
				tableMapping = table;
				break;
			}
		}
		return tableMapping;
	}
}
