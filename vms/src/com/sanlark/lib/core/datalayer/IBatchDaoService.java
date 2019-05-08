/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Ganesh Kshirsagar
 * @Version    : 1.0.1
 * @CreateDate : Jul 14, 2015
 */
package com.sanlark.lib.core.datalayer;

import java.util.List;

public interface IBatchDaoService<T> {
	void update(String tableName, List<T> insertCol, List<T> updateCol, Long userRefId)throws TransactionException;
	List<T> list(String tableName);
}
