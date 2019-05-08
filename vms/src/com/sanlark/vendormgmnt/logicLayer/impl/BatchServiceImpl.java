package com.sanlark.vendormgmnt.logicLayer.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sanlark.lib.core.datalayer.IBatchDaoService;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.vendormgmnt.datalayer.BatchTableMapping;
import com.sanlark.vendormgmnt.logicLayer.IBatchService;
import com.sanlark.vendormgmnt.pojo.common.BatchTableInfo;

public class BatchServiceImpl implements IBatchService{

	@Autowired
	@Qualifier ("batchDao")
	private IBatchDaoService<BatchTableInfo> dao;
	
	@Override
	public boolean update(BatchTableMapping batchTable, 
			List<BatchTableInfo> col, AccessTicket accessTicket) {
		boolean result = false;
		List<BatchTableInfo> insertCol = new ArrayList<BatchTableInfo>();
		List<BatchTableInfo> updateCol = new ArrayList<BatchTableInfo>();
		
		for (BatchTableInfo obj : col) {
			if(obj.getRecordId() == null || obj.getRecordId() == -1){
				if(CommonUtil.isEmpty(obj.getTitle()) == false){
					insertCol.add(obj);
				}
			}else if(obj.getRecordId() != null && obj.getRecordId() != 0){
				updateCol.add(obj);
			}
		}
		try {
			dao.update(batchTable.tableName, insertCol, 
					updateCol, accessTicket.getRecordId());
			result = true;
		} catch (TransactionException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BatchTableInfo> getList(BatchTableMapping batchTable) {
		return dao.list(batchTable.tableName);
	}
}
