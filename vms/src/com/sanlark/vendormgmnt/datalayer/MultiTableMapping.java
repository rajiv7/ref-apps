/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 03-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import com.sanlark.lib.core.datalayer.IMappingTable;

public enum MultiTableMapping implements IMappingTable{
	REQUEST_SKILL_MAP("batch_request_skill_map", "request_id", "skill_id"),
	VENDOR_SKILL_MAP("master_vendor_skill_map", "vendor_id", "skill_id"),
	;
	
	private MultiTableMapping(String tableName, String keyClmName, String valueClmName){
		this.tableName = tableName;
		this.keyClmName = keyClmName;
		this.valueClmName = valueClmName;
	}
	
	public final String tableName;
	public final String keyClmName;
	public final String valueClmName;
	
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
		return false;
	}
}
