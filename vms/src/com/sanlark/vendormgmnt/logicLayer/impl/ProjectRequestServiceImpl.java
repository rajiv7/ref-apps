/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 01-Jul-2015 
 */
package com.sanlark.vendormgmnt.logicLayer.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanlark.lib.core.datalayer.AccessDeniedException;
import com.sanlark.lib.core.datalayer.IMappingTableDaoLoader;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.core.util.DateUtil;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.lib.web.pojo.UserRole;
import com.sanlark.lib.web.util.EnumUtil;
import com.sanlark.vendormgmnt.datalayer.IProjectRequestDao;
import com.sanlark.vendormgmnt.datalayer.MultiTableMapping;
import com.sanlark.vendormgmnt.logicLayer.IProjectRequestService;
import com.sanlark.vendormgmnt.logicLayer.RequestStatusUtil;
import com.sanlark.vendormgmnt.pojo.common.RequestStatusUpdateInfo;
import com.sanlark.vendormgmnt.pojo.dtls.ModelProjectRequest;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;
import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;
import com.sanlark.vendormgmnt.vo.common.VendorProposalServiceRequest;
import com.sanlark.vendormgmnt.vo.common.VoProjectRequest;
import com.sanlark.vendormgmnt.vo.common.VoVendorSkillCount;

public class ProjectRequestServiceImpl implements IProjectRequestService{

	@Autowired
	private IProjectRequestDao requestDao;
	
	@Autowired
	private IMappingTableDaoLoader mappingTableDao;
	
	@Override
	public boolean create(ModelProjectRequest obj, AccessTicket accessTicket) {
		boolean result = false;
		try {
			obj.setStatusId(RequestStatus.SUBMITTED_TO_GTEC);
			obj.setCreatedById(accessTicket.getRecordId());
			requestDao.create(obj);
			// TODO -- TEENAM -- SEND EMAIL TO GETC
			result = true;
		} catch (TransactionException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean update(ModelProjectRequest obj, AccessTicket accessTicket) {
		boolean result = false;
		try {
			RequestStatus requestStatus = getStatus(obj.getRecordId());
			if(RequestStatusUtil.isUpdateAllowed(requestStatus)){
				obj.setUpdatedById(accessTicket.getRecordId());
				requestDao.update(obj);
				// TODO -- TEENAM -- SEND EMAIL TO GETC
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ModelProjectRequest get(AccessTicket accessTicket, long recordId)throws AccessDeniedException {
		ModelProjectRequest obj = requestDao.get(recordId);
		if(obj == null){
			return null;
		}
		
		if(accessTicket.getUserRole() == UserRole.REQUESTER
				&& accessTicket.getRecordId().equals(obj.getCreatedById()) == false){
			throw new AccessDeniedException();
		}
		
		// Load all mapped skills
		List<Long> col = mappingTableDao.getValueMap(MultiTableMapping.REQUEST_SKILL_MAP, obj.getRecordId());
		int[] skills = CommonUtil.toIntArray(col);
		obj.setSkills(skills);
		return obj;
	}
	
	@Override
	public VoProjectRequest get(Long recordId){
		VoProjectRequest vo = requestDao.view(recordId);
		if(vo != null){
			if(RequestStatusUtil.isVendorSelected(vo.getRequestStatus())){
				String selectedVendor = requestDao.getSelectedVendor(recordId);
				vo.setSelectedVendor(selectedVendor);
			}
		}
		return vo;
	}

	@Override
	public List<VoProjectRequest> filter(AccessTicket accessTicket, String title, String dateRange) {
		StringBuilder filterQuery = new StringBuilder();
		if(accessTicket.getUserRole() == UserRole.REQUESTER){
			filterQuery.append(" and r.created_by_id = "+accessTicket.getRecordId());
		}
		
		if(CommonUtil.isEmpty(title) == false){
			filterQuery.append(" and r.request_title like '%" + title + "%'");
		}
		
		if(CommonUtil.isEmpty(dateRange) == false){
			try{
				String[] dateRangeArr = DateUtil.getDateRange(dateRange);
				filterQuery.append(" and (r.created_date between '" + dateRangeArr[0]);
				filterQuery.append("' and '" + dateRangeArr[1] + "')");
			}catch(Exception ex){
				System.out.println("Invalid date format passed");
			}
		}
		return requestDao.filter(filterQuery.toString());
	}

	@Override
	public List<VoProjectRequest> filterByTitle(AccessTicket accessTicket, String title) {
		return filter(accessTicket, title, null);
	}

	@Override
	public List<VoProjectRequest> filterByDate(AccessTicket accessTicket, String dateRange) {
		return filter(accessTicket, null, dateRange);
	}
	
	@Override
	public List<VoProjectRequest> getList(AccessTicket accessTicket) {
		String filterQuery = null;
		if(accessTicket.getUserRole() == UserRole.REQUESTER){
			filterQuery = " and r.created_by_id = " + accessTicket.getRecordId();
		}
		return requestDao.filter(filterQuery);
	}

	@Override
	public RequestStatus getStatus(Long recordId) {
		int currentStatusId = requestDao.getStatus(recordId);
		return EnumUtil.get(RequestStatus.class, currentStatusId) ;
	}

	@Override
	public String updateStatus(RequestStatusUpdateInfo updateInfo) {
		String errorMsg = null;
		try{
			requestDao.updateStatus(updateInfo);
			// TODO -- TEENAM -- SEND mails, based on newRequestStatus
		}catch(Exception ex){
			ex.printStackTrace();
			errorMsg = ex.getMessage();
		}	
		return errorMsg;
	}

	@Override
	public VoProjectRequest getDetailsForVendorSelection(Long recordId) {
		VoProjectRequest vo = get(recordId);
		if(vo == null){
			return null;
		}
		
		List<VoVendorSkillCount> vendorCol = requestDao.getMatchedVendors(recordId);
		vo.setMatchedVendors(vendorCol);
		if(CommonUtil.hasElement(vendorCol)){
			// Load Sent mails
			List<Long> vendorsIds = requestDao.getSentProposalByRequestId(recordId);
			if(CommonUtil.hasElement(vendorsIds)){
				for(VoVendorSkillCount v : vendorCol){
					if(vendorsIds.contains(v.getRecordId())){
						v.setMailSent(true);
					}
				}
			}
		}
		
		// TODO -- GANESH -- Load Feedback
		return vo;
	}

	@Override
	public void sendProposal(VendorProposalServiceRequest proposalRequest) {
		/*
		 * 1. Get vendors' email
		 * 2. Send email to the vendors
		 * 3. Save the details into batch_table
		 */
		List<Long> vendorCol = proposalRequest.getRequestVendorCol();
		
		List<PersonInfo> vendorContactCol = requestDao.getVendorContact(vendorCol);
		if(CommonUtil.hasElement(vendorContactCol) == false){
			String prefix = vendorCol.size() == 1 ? "The selected vendor does" : "The selected vendors do";
			proposalRequest.setError(prefix + " not have any contact. Please contact administrator");
			return;
		}
		try {
			List<KeyValPair<Long, String>> contactCol = 
					new ArrayList<KeyValPair<Long,String>>(vendorContactCol.size());
			
			for(PersonInfo vo : vendorContactCol){
				KeyValPair<Long, String> contact = new KeyValPair<Long, String>();
				contact.setKey(vo.getVendorId());
				// TODO -- Split if there are 2 email associated
				contact.setValue(vo.getEmail());
				contactCol.add(contact);
				// TODO -- TEENAM -- Send email
				System.out.println("Mail sent to " + contact);
			}
			requestDao.sendProposal(proposalRequest.getRequestId(), contactCol);
		} catch (TransactionException e) {
			e.printStackTrace();
			proposalRequest.setError(e.getMessage());
		}
	}

	@Override
	public List<ComboOption> getVendorFromSentProposal(Long requestId) {
		return requestDao.getVendorFromSentProposal(requestId);
	}
}
