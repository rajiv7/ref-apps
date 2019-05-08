/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.lib.core.datalayer;

public interface IMappingTable {
	String getTableName();
	String getKeyClmName();
	String getValueClmName();
	boolean isFilterInactive();
}
