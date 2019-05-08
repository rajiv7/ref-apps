/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 30-Jun-2015 
 */
package com.sanlark.vendormgmnt.logicLayer;

import java.util.List;

import com.sanlark.lib.core.datalayer.IMappingTable;
import com.sanlark.lib.web.pojo.ComboOption;

public interface IComboOptionService {
	List<ComboOption> load(IMappingTable mappingTable);
}
