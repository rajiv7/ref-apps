/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.vendormgmnt.controller.common;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sanlark.lib.core.datalayer.AccessDeniedException;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.core.util.DateUtil;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.lib.web.pojo.PageMsg;
import com.sanlark.lib.web.pojo.ResponseCode;
import com.sanlark.lib.web.pojo.UserRole;
import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.EnumUtil;
import com.sanlark.lib.web.util.ResponseUtil;
import com.sanlark.lib.web.util.SessionUtil;
import com.sanlark.lib.web.util.WebUtil;
import com.sanlark.vendormgmnt.datalayer.BatchTableMapping;
import com.sanlark.vendormgmnt.form.FormFilterSearchRequest;
import com.sanlark.vendormgmnt.form.FormSendProposal;
import com.sanlark.vendormgmnt.form.FormUpdateProjectRequest;
import com.sanlark.vendormgmnt.logicLayer.IComboOptionService;
import com.sanlark.vendormgmnt.logicLayer.IProjectRequestService;
import com.sanlark.vendormgmnt.logicLayer.RequestStatusUtil;
import com.sanlark.vendormgmnt.logicLayer.UserRoleUtil;
import com.sanlark.vendormgmnt.pojo.common.ProjectDuration;
import com.sanlark.vendormgmnt.pojo.common.RequestStatusUpdateInfo;
import com.sanlark.vendormgmnt.pojo.dtls.ModelProjectRequest;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;
import com.sanlark.vendormgmnt.vo.common.VendorProposalServiceRequest;
import com.sanlark.vendormgmnt.vo.common.VoProjectRequest;

@Controller
public class ActionProcRequestManagement {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IComboOptionService comboService;
	
	@Autowired
	private IProjectRequestService requestService;
	
	@RequestMapping(value="UserServlet/searchRequests.html")
	public ModelAndView list(HttpServletRequest request, HttpSession session){
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, "Procurement Requests");
		
		FormFilterSearchRequest form = SessionUtil.get(session, "searchForm");
		filter(request, form);
		
		return new ModelAndView("body.page.requests.searchRequests");
	}
	
	@RequestMapping(value="UserServlet/filterProcRequests.html", method=RequestMethod.POST)
	public String filter(@ModelAttribute("frmSearchFilter") FormFilterSearchRequest form, 
			BindingResult result, HttpServletRequest request){
		filter(request, form);
		return "ppr.requestSearchResult";
	}
	
	/**
	 * @param request
	 * @param form
	 */
	private void filter(HttpServletRequest request, FormFilterSearchRequest form) {
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		
		boolean isInitDateRange = false;
		
		// Validate the form
		if(form == null){
			form = new FormFilterSearchRequest();
			isInitDateRange = true;
		}else if(form.isChkDateRange()){
			if(CommonUtil.isEmpty(form.getTxtDateRange())){
				isInitDateRange = true;
			}else{
				try{
					DateUtil.getDateRange(form.getTxtDateRange());
				}catch(Exception ex){
					System.out.println("Invalid date format passed");
					isInitDateRange = true;
				}
			}
		}else if(form.isChkDateRange() == false && form.isChkTitle() == false){
			isInitDateRange = true;
		}
		
		if(isInitDateRange){
			form.setChkDateRange(true);
			form.setTxtDateRange(DateUtil.getDateRange());
		}
		
		List<VoProjectRequest> col = null;
		
		if(form.isChkDateRange() && form.isChkTitle()){
			col = requestService.filter(accessTicket, form.getTxtTitle(), form.getTxtDateRange());
		}else if(form.isChkDateRange()){
			col = requestService.filterByDate(accessTicket, form.getTxtDateRange());
		}else if(form.isChkTitle()){
			col = requestService.filterByTitle(accessTicket, form.getTxtTitle());
		}else{
			 col = requestService.getList(accessTicket);
		}
		
		for(VoProjectRequest vo : col){
			String accessId = WebUtil.mergeRecordId(vo.getRecordId(), vo.getCreatedById());
			vo.setAccessId(accessId);
			if(accessTicket.getRecordId().equals(vo.getCreatedById())){
				vo.setEditAllowed(RequestStatusUtil.isUpdateAllowed(vo.getRequestStatus()));
			}
		}

		SessionUtil.set(request, "searchForm", form);
		request.setAttribute(AppConfigConst.PAGE_DATA, col);
	}

	@RequestMapping(value="UserServlet/createRequest.html", method=RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request){
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		return getRecordPage(accessTicket, request, null);
	}
	
	@RequestMapping(value="UserServlet/createRequest.html", method=RequestMethod.POST)
	public ModelAndView doPost(@ModelAttribute("FormRequest") ModelProjectRequest form, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, Locale locale){
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		PageMsg pageMsg = form.validate();
		
		if(pageMsg.isError() == false){
			boolean isSuccess = requestService.create(form, accessTicket);
			if(isSuccess){
				ResponseUtil.setOneTimeMsg(request, "msg.request.creation.success", messageSource, locale);
				return new ModelAndView("redirect:/UserServlet/searchRequests.html");
			}
			pageMsg.setError("msg.request.creation.failed");
		}
		ResponseUtil.setPageMsg(request, pageMsg, messageSource, locale);
		return getRecordPage(accessTicket, request, form);
	}
	
	@RequestMapping(value="UserServlet/viewRequest.html")
	public ModelAndView viewRequest(@RequestParam("refId") String accessId, 
			HttpServletRequest request, Locale locale){
		String errorKey = "access.denied.get.invalid";
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		
		if(recordInfo != null){
			AccessTicket accessTicket = SessionUtil.getTicket(request);
			if(accessTicket.getRecordId().equals(recordInfo.getUserRefId())
					|| accessTicket.getUserRole() != UserRole.REQUESTER){
				VoProjectRequest vo = requestService.get(recordInfo.getKey());
				if(vo != null){
					vo.setAccessId(accessId);
					
					if(accessTicket.getRecordId().equals(vo.getCreatedById())){
						if(RequestStatusUtil.isUpdateAllowed(vo.getRequestStatus())){
							request.setAttribute("isEditAllowed", true);
						}
						if(RequestStatusUtil.isSendProposalAllowed(vo.getRequestStatus())){
							request.setAttribute("isSendProposalAllowed", true);
						}
					}
					
					if(UserRoleUtil.isGtec(accessTicket)){
						if(RequestStatusUtil.isGtecAllowed(vo.getRequestStatus())){
							request.setAttribute("isGtecAllowed", true);
						}
					}else if(UserRoleUtil.isProcumentor(accessTicket)){
						if(RequestStatusUtil.isProcAllowed(vo.getRequestStatus())
								|| accessTicket.getRecordId().equals(vo.getCreatedById())){
							request.setAttribute("isProcAllowed", true);
							
							boolean isProcCanUpdate = RequestStatusUtil.isProcCanUpdate(vo.getRequestStatus());
							if(isProcCanUpdate){
								List<ComboOption> col = requestService.getVendorFromSentProposal(recordInfo.getKey());
								request.setAttribute("selectedVendorCol", col);
								request.setAttribute("isProcCanUpdate", isProcCanUpdate);
							}
							
							request.setAttribute("isCompleteAllowed", RequestStatusUtil.isCompleteAllowed(vo.getRequestStatus()));
						}
					}
					
					/*if(RequestStatusUtil.isVendorSelected(vo.getRequestStatus())){
						request.setAttribute("isVendorNameAvailable", DateUtil.getUiDate());
					}*/
					
					request.setAttribute(AppConfigConst.CURRENT_DATE, DateUtil.getUiDate());
					request.setAttribute(AppConfigConst.PAGE_DATA, vo);
					request.setAttribute(AppConfigConst.BODY_TITLE_KEY, "Request Details");
					return new ModelAndView("body.page.requests.viewRequest");
				}
			}
		}
		ResponseUtil.setOneTimeMsg(request, errorKey, true, messageSource, locale);
		return new ModelAndView("redirect:/UserServlet/searchRequests.html");
	}
	
	@RequestMapping(value="UserServlet/sendProposal.html", method=RequestMethod.GET)
	public ModelAndView sendProposalGet(@RequestParam("refId") String accessId, 
			HttpServletRequest request, Locale locale){
		String errorKey = "access.denied.get.invalid";
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		
		if(recordInfo != null){
			AccessTicket accessTicket = SessionUtil.getTicket(request);
			if(accessTicket.getRecordId().equals(recordInfo.getUserRefId())
					|| accessTicket.getUserRole() != UserRole.REQUESTER){
				VoProjectRequest vo = requestService.getDetailsForVendorSelection(recordInfo.getKey());
				if(vo != null){
					vo.setAccessId(accessId);

					WebUtil.buildAccessId(vo.getMatchedVendors());
					
					if(accessTicket.getRecordId().equals(vo.getCreatedById())){
						if(RequestStatusUtil.isSendProposalAllowed(vo.getRequestStatus())){
							request.setAttribute(AppConfigConst.PAGE_DATA, vo);
							request.setAttribute(AppConfigConst.BODY_TITLE_KEY, "Vender Selection");
							return new ModelAndView("body.page.requests.vendorSelection");
						}
					}
				}
			}
		}
		ResponseUtil.setOneTimeMsg(request, errorKey, true, messageSource, locale);
		return new ModelAndView("redirect:/UserServlet/searchRequests.html");
	}
	
	@RequestMapping(value="UserServlet/sendProposal.html", method=RequestMethod.POST)
	public ModelAndView sendProposalPost(@ModelAttribute("FormRequest") FormSendProposal form, 
			BindingResult result, HttpServletRequest request, Locale locale){
		PageMsg pageMsg = form.validate();
		
		if(pageMsg.isError() == false){
			KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(form.getRefId());
			if(recordInfo != null){
				RequestStatus currentStatus = requestService.getStatus(recordInfo.getKey());
				if(RequestStatusUtil.isSendProposalAllowed(currentStatus) == false){
					return new ModelAndView("redirect:/UserServlet/viewRequest.html?refId=" + form.getRefId());
				}
				
				List<Long> vendorCol = WebUtil.parseAccessId(form.getVendors());
				VendorProposalServiceRequest proposalRequest = new VendorProposalServiceRequest(
						recordInfo.getKey(), vendorCol);
				
				requestService.sendProposal(proposalRequest);
				if(proposalRequest.isError()){
					pageMsg.setError(proposalRequest.getError());
				}
			}else{
				pageMsg.setError("Invalid Access");
			}
		}
		
		if(pageMsg.isError() == false){
			String successMsg = form.getVendors().size() == 0 ? "Proposal" : "Proposals";
			successMsg += " sent successfully";
			pageMsg.setSuccessMsg(successMsg);
		}
		
		ResponseUtil.setOneTimeMsg(request, pageMsg);
		return new ModelAndView("redirect:/UserServlet/sendProposal.html?f=1&refId=" + form.getRefId());
	}
	
	/*
	 * The below update is only allowed if the user is created the request
	 */
	
	@RequestMapping(value="UserServlet/updateRequest.html", method=RequestMethod.GET)
	public ModelAndView manageRequest(@RequestParam("refId") String accessId, 
			HttpServletRequest request, Locale locale){
		String errorKey = "access.denied.get.invalid";
		
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
	
		if(recordInfo != null){
			try {
				if(accessTicket.getRecordId().equals(recordInfo.getUserRefId())){
					ModelProjectRequest form = requestService.get(accessTicket, recordInfo.getKey());
					if(form != null){
						if(RequestStatusUtil.isUpdateAllowed(form.getStatusId())){
							form.setAccessId(accessId);
							return getRecordPage(accessTicket, request, form);
						}else{
							return new ModelAndView("redirect:/UserServlet/viewRequest.html?refId=" + accessId);
						}
					}
				}
			} catch (AccessDeniedException e) {
				errorKey = e.getErrorKey();
			}
		}
		ResponseUtil.setOneTimeMsg(request, errorKey, true, messageSource, locale);
		return new ModelAndView("redirect:/UserServlet/searchRequests.html");
	}
	
	/*
	 * This will be called by the person who has created the request
	 */
	@RequestMapping(value="UserServlet/updateRequest.html", method=RequestMethod.POST)
	public ModelAndView doPostUpdate(@ModelAttribute("FormRequest") ModelProjectRequest form, BindingResult result,
			@RequestParam("refId") String accessId, HttpServletRequest request, HttpServletResponse response, Locale locale){
		
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		PageMsg pageMsg = form.validate();
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		if(recordInfo == null || accessTicket.getRecordId().equals(recordInfo.getUserRefId()) == false){
			pageMsg.setError("access.denied.get.invalid");
		}
		
		if(pageMsg.isError() == false){
			RequestStatus currentStatus = requestService.getStatus(recordInfo.getKey());
			if(RequestStatusUtil.isUpdateAllowed(currentStatus) == false){
				return new ModelAndView("redirect:/UserServlet/viewRequest.html?refId=" + accessId);
			}
			
			form.setRecordId(recordInfo.getKey());
			// Save the details
			boolean isSuccess = requestService.update(form, accessTicket);
			if(isSuccess){
				ResponseUtil.setOneTimeMsg(request, "msg.request.update.success", messageSource, locale);
				return new ModelAndView("redirect:/UserServlet/searchRequests.html");
			}
			pageMsg.setError("msg.request.creation.failed");
		}
		ResponseUtil.setPageMsg(request, pageMsg, messageSource, locale);
		return getRecordPage(accessTicket, request, form);
	}
	
	@RequestMapping(value="UserServlet/updateRequestStatus.html", method=RequestMethod.POST)
	public void updateRequestStatus(@ModelAttribute("frmUpdateProcRequest") FormUpdateProjectRequest form, 
			BindingResult result, HttpServletRequest request, HttpServletResponse response, Locale locale){
		
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		
		String errorMessage = null;
		
		RequestStatus newRequestStatus = EnumUtil.get(RequestStatus.class, form.getActionCmd());
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(form.getRefId());
		if(recordInfo != null){
			RequestStatus currentStatus = requestService.getStatus(recordInfo.getKey());
			
			if(UserRoleUtil.isGtec(accessTicket)){
				if(RequestStatusUtil.isGtecAllowed(currentStatus) &&
						RequestStatusUtil.isGtecActionAllowed(newRequestStatus)){
					// TODO -- Verify Close Date
					form.setVendorId(AppConfigConst.GTEC_VENDOR_ID);
				}else{
					errorMessage = "Not Allowed. Please contact administrator";
				}
			}else if(UserRoleUtil.isProcumentor(accessTicket)){
				if(RequestStatusUtil.isProcCanUpdate(currentStatus)){
					// TODO -- Verify vendor Id, and close date
				}else if(RequestStatusUtil.isCompleteAllowed(currentStatus)){
					// TODO -- Verify Delivery Date
				}else{
					errorMessage = "Not Allowed. Please contact administrator";
				}
			}else{
				errorMessage = "Invalid Access";
			}
		}else{
			errorMessage = "Access Denied";
		}
		
		if(errorMessage != null){
			if(form.isAjaxCall()){
				ResponseUtil.setOneTimeMsg(request, errorMessage, true);
				ResponseUtil.serialize(response, errorMessage, ResponseCode.REDIRECT);
			}else{
				String redirectPath = "viewRequest.html?refId=" + form.getRefId();
				ResponseUtil.redirect(request, response, redirectPath, errorMessage, true);
			}
			return;
		}
		

		if(errorMessage == null){
			RequestStatusUpdateInfo updateInfo = new RequestStatusUpdateInfo(
					recordInfo.getKey(), accessTicket, newRequestStatus);
			updateInfo.setVendorId(form.getVendorId());
			updateInfo.setTxnDate(form.getTxnDate());
			updateInfo.setPo(form.getPo());
			errorMessage = requestService.updateStatus(updateInfo);
		}
		
		if(errorMessage != null){
			if(form.isAjaxCall()){
				ResponseUtil.serialize(response, errorMessage, ResponseCode.FAILED);
			}else{
				ResponseUtil.sendErrorHtml(response, errorMessage, ResponseCode.FAILED);
			}
			return;
		}
		
		String msg = "Updated successfully";
		
		if(form.isAjaxCall()){
			boolean isMarkedComplted = (newRequestStatus == RequestStatus.ACCEPTED_BY_GTEC
					|| newRequestStatus == RequestStatus.DECLINED_BY_GTEC
					|| newRequestStatus == RequestStatus.WORK_COMPLETED
					|| newRequestStatus == RequestStatus.CLOSED);
			Object[] respMsg = new Object[]{
				msg, isMarkedComplted, newRequestStatus.uiClass, newRequestStatus.fullCaption
			};
			ResponseUtil.serialize(response, respMsg);
		}else{
			String redirectPath = "viewRequest.html?refId=" + form.getRefId();
			ResponseUtil.redirect(request, response, redirectPath, msg);
		}
	}
	
	/*
	 * Below are the helper methods of this controller
	 */

	/**
	 * @param request
	 * @param form
	 * @return
	 */
	private ModelAndView getRecordPage(AccessTicket accessTicket, HttpServletRequest request, ModelProjectRequest form) {
		if(form == null){
			form = new ModelProjectRequest();
			form.setStartDate(DateUtil.getUiDate());
		}
		// Populate Combo Options
		loadComboDtls(request);
		request.setAttribute(AppConfigConst.PAGE_DATA, form);
		
		String bodyTitle = form.isNewRecord() ? "Create Request" : "Update Request";
		
		String submitUlPath = "createRequest" + AppConfigConst.ACTION_SUFFIX;
		if(form.isNewRecord() == false){
			submitUlPath = "updateRequest" + AppConfigConst.ACTION_SUFFIX + "?refId=" + form.getAccessId();
		}
		request.setAttribute("submitUlPath", submitUlPath);
		
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, bodyTitle);
		return new ModelAndView("body.page.requests.manageRequest");
	}

	private void loadComboDtls(HttpServletRequest request) {
		WebUtil.populate(comboService, request, "categoryCol", BatchTableMapping.SERVICE_CATEGORY);
		WebUtil.populate(comboService, request, "locationCol", BatchTableMapping.SERVICE_LOCATION);
		WebUtil.populate(comboService, request, "skillCol", BatchTableMapping.SERVICE_SKILL);
		
		List<ComboOption> col = EnumUtil.load(ProjectDuration.class);
		request.setAttribute("durationCol", col);
	}
}
