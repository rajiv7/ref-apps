/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.core.datalayer;

import java.util.List;

public interface IDaoService<T, VO> {
	void create(T obj) throws TransactionException;
	void update(T obj) throws TransactionException;
	void remove(T obj) throws TransactionException;
	T get(long recordId);
	List<T> list(String dbFilterQuery);
	
    List<VO> filter(String dbFilterQuery);
	/**
	 * @param recordId
	 */
	VO view(long recordId);
}
