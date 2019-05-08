/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.logicLayer.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sanlark.lib.core.datalayer.IMappingTable;
import com.sanlark.lib.core.datalayer.IMappingTableDaoLoader;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.vendormgmnt.logicLayer.IComboOptionService;

public class ComboOptionServiceImpl implements IComboOptionService{
	
	@Autowired
	@Qualifier("mappingTableDao")
	private IMappingTableDaoLoader dao;
	
	@Override
	public List<ComboOption> load(IMappingTable mappingTable) {
		List<ComboOption> col = dao.load(mappingTable);
		return col != null ? col : new ArrayList<ComboOption>();
	}
}
