/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.logicLayer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanlark.lib.core.datalayer.IMappingTableDaoLoader;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.vendormgmnt.datalayer.IVendorDao;
import com.sanlark.vendormgmnt.datalayer.MultiTableMapping;
import com.sanlark.vendormgmnt.logicLayer.IVendorService;
import com.sanlark.vendormgmnt.pojo.vendor.ModelVendorInfo;
import com.sanlark.vendormgmnt.vo.common.VoVendorInfo;

public class VendorServiceImpl implements IVendorService{
	
	@Autowired
	private IVendorDao vendorDao;
	
	@Autowired
	private IMappingTableDaoLoader mappingTableDao;
	
	@Override
	public boolean create(ModelVendorInfo obj, AccessTicket accessTicket) {
		boolean result = false;
		try {
			obj.setCreatedById(accessTicket.getRecordId());
			vendorDao.create(obj);
			result = true;
		} catch (TransactionException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>
	 * This method updates vendor info, its skills and attachments only
	 * </p>
	 * <p>
	 * Updating the contact will be a new service, because it would a little complicated on the UI
	 * </p> 
	 */
	@Override
	public boolean update(ModelVendorInfo obj, AccessTicket accessTicket) {
		boolean result = false;
		try {
			obj.setUpdatedById(accessTicket.getRecordId());
			vendorDao.update(obj);
			result = true;
		} catch (TransactionException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<VoVendorInfo> getNameList() {
		return vendorDao.getNameList();
	}

	@Override
	public VoVendorInfo view(Long recordId) {
		return vendorDao.view(recordId);
	}

	@Override
	public ModelVendorInfo get(Long recordId) {
		ModelVendorInfo obj = vendorDao.get(recordId);
		if(obj == null){
			return null;
		}
		// Attachments/Contact Persons already loaded
		
		// Load all mapped skills
		List<Long> col = mappingTableDao.getValueMap(MultiTableMapping.VENDOR_SKILL_MAP, obj.getRecordId());
		int[] skills = CommonUtil.toIntArray(col);
		obj.setSkills(skills);
		
		return obj;
	}

	@Override
	public List<String> getSkills(Long recordId) {
		return vendorDao.getSkills(recordId);
	}
}
