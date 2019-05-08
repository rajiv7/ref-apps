/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Ganesh Kshirsagar
 * @Version    : 1.0.1
 * @CreateDate : Jul 14, 2015
 */
package com.sanlark.vendormgmnt.logicLayer;

import java.util.List;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.vendormgmnt.datalayer.BatchTableMapping;
import com.sanlark.vendormgmnt.pojo.common.BatchTableInfo;

public interface IBatchService {
	boolean update(BatchTableMapping batchTable, 
			List<BatchTableInfo> col, AccessTicket accessTicket);
	List<BatchTableInfo> getList(BatchTableMapping batchTable);
}
