/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 03-Jul-2015 
 */
package com.sanlark.vendormgmnt.logicLayer;

import java.util.List;

import com.sanlark.lib.core.datalayer.AccessDeniedException;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.vendormgmnt.pojo.common.RequestStatusUpdateInfo;
import com.sanlark.vendormgmnt.pojo.dtls.ModelProjectRequest;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;
import com.sanlark.vendormgmnt.vo.common.VendorProposalServiceRequest;
import com.sanlark.vendormgmnt.vo.common.VoProjectRequest;

public interface IProjectRequestService{
	/**
	 * This method will be called by the REQUESTER only
	 * @param obj
	 * @param accessTicket
	 * @return
	 */
	boolean create(ModelProjectRequest obj, AccessTicket accessTicket);

	/**
	 * This method will be called by the REQUESTER only
	 * 
	 * @param form
	 * @param accessTicket
	 * @return
	 */
	boolean update(ModelProjectRequest form, AccessTicket accessTicket);
	
	/**
	 * @param accessTicket
	 * @param recordId
	 * @return
	 * @throws AccessDeniedException
	 */
	ModelProjectRequest get(AccessTicket accessTicket, long recordId) throws AccessDeniedException;
	
	List<VoProjectRequest> getList(AccessTicket accessTicket);
	
	List<VoProjectRequest> filter(AccessTicket accessTicket, String title, String dateRange);
	List<VoProjectRequest> filterByTitle(AccessTicket accessTicket, String title);
	List<VoProjectRequest> filterByDate(AccessTicket accessTicket, String dateRange);
	
	VoProjectRequest get(Long requestId);

	RequestStatus getStatus(Long requestId);

	String updateStatus(RequestStatusUpdateInfo updateInfo);

	VoProjectRequest getDetailsForVendorSelection(Long requestId);

	void sendProposal(VendorProposalServiceRequest proposalRequest);
	
	List<ComboOption> getVendorFromSentProposal(Long requestId);
}
