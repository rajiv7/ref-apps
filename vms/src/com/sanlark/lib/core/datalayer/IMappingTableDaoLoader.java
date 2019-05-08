/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.lib.core.datalayer;

import java.util.List;

import com.sanlark.lib.web.pojo.ComboOption;

public interface IMappingTableDaoLoader {
	long load(IMappingTable mappingTable, String keyClmValue);
	
	List<ComboOption> load(IMappingTable mappingTable);
	
	<I, O> List<O> getValueMap(IMappingTable mappingTable, I keyClmValue);
	<I,O> List<O> getMap(String query, I keyClmValue);
}
