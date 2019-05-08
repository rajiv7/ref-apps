/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import java.util.List;

import com.sanlark.lib.core.datalayer.IDaoService;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.vendormgmnt.pojo.common.RequestStatusUpdateInfo;
import com.sanlark.vendormgmnt.pojo.dtls.ModelProjectRequest;
import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;
import com.sanlark.vendormgmnt.vo.common.VoProjectRequest;
import com.sanlark.vendormgmnt.vo.common.VoVendorSkillCount;

public interface IProjectRequestDao extends IDaoService<ModelProjectRequest, VoProjectRequest>{

	/**
	 * @param recordId
	 * @return
	 */
	int getStatus(Long recordId);

	/**
	 * 
	 * @param newRequestStatus
	 * @param form
	 * @throws TransactionException
	 */
	void updateStatus(RequestStatusUpdateInfo updateInfo)throws TransactionException;

	List<VoVendorSkillCount> getMatchedVendors(Long recordId);

	List<PersonInfo> getVendorContact(List<Long> vendorCol);
	
	/**
	 * Update multiple
	 * @param requestId
	 * @param vendorCol
	 * @throws TransactionException
	 */
	void sendProposal(Long requestId, List<KeyValPair<Long, String>> contactCol)throws TransactionException;

	List<Long> getSentProposalByRequestId(Long recordId);

	List<ComboOption> getVendorFromSentProposal(Long requestId);

	String getSelectedVendor(Long recordId);
}
