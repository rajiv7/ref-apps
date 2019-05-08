/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import java.util.List;

import com.sanlark.lib.core.datalayer.IDaoService;
import com.sanlark.vendormgmnt.pojo.vendor.ModelVendorInfo;
import com.sanlark.vendormgmnt.vo.common.VoVendorInfo;

public interface IVendorDao extends IDaoService<ModelVendorInfo, VoVendorInfo>{
	List<VoVendorInfo> getNameList();

	List<String> getSkills(Long recordId);
}
