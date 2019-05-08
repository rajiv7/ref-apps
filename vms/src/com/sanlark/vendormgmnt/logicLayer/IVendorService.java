/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.logicLayer;

import java.util.List;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.vendormgmnt.pojo.vendor.ModelVendorInfo;
import com.sanlark.vendormgmnt.vo.common.VoVendorInfo;

public interface IVendorService {
	boolean create(ModelVendorInfo obj, AccessTicket accessTicket);
	boolean update(ModelVendorInfo obj, AccessTicket accessTicket);
	List<VoVendorInfo> getNameList();
	ModelVendorInfo get(Long recordId);
	VoVendorInfo view(Long recordId);
	List<String> getSkills(Long recordId);
}
