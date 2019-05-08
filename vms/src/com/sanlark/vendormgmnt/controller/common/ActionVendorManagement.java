/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 08-Jul-2015 
 */
package com.sanlark.vendormgmnt.controller.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.lib.web.pojo.PageData;
import com.sanlark.lib.web.pojo.PageMsg;
import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.ResponseUtil;
import com.sanlark.lib.web.util.SessionUtil;
import com.sanlark.lib.web.util.WebUtil;
import com.sanlark.vendormgmnt.datalayer.BatchTableMapping;
import com.sanlark.vendormgmnt.logicLayer.IComboOptionService;
import com.sanlark.vendormgmnt.logicLayer.IVendorService;
import com.sanlark.vendormgmnt.logicLayer.UserRoleUtil;
import com.sanlark.vendormgmnt.pojo.vendor.ModelVendorInfo;
import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;
import com.sanlark.vendormgmnt.vo.common.VoVendorInfo;

@Controller
public class ActionVendorManagement {
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IComboOptionService comboService;
	
	@Autowired
	private IVendorService vendorService;
	
	@RequestMapping(value="UserServlet/searchVendors.html")
	public ModelAndView services(HttpServletRequest request){
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, "Search Vendors");
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		List<VoVendorInfo> nameCol = vendorService.getNameList();
		
		for(VoVendorInfo vo : nameCol){
			String accessId = WebUtil.mergeRecordId(vo.getRecordId(), accessTicket.getRecordId());
			vo.setAccessId(accessId);
		}
		
		request.setAttribute(AppConfigConst.PAGE_DATA, nameCol);
		return new ModelAndView("body.page.vendor.searchVendors");
	}
	
	@RequestMapping(value="UserServlet/showVendorInfo.html", method=RequestMethod.POST)
	public String showVendorInfo(@RequestParam("refId") String accessId,
			HttpServletRequest request, HttpServletResponse response, Locale locale){
		PageData pageData = new PageData();
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		if(recordInfo != null){
			List<String> skillCol = vendorService.getSkills(recordInfo.getKey());
			if(skillCol == null){
				pageData.setErrorMsg("Details not available. Please try some other time");
			}else{
				pageData.setData(skillCol);
			}
		}else{
			pageData.setErrorMsg("Access Denied");
		}
		request.setAttribute(AppConfigConst.PAGE_DATA, pageData);
		return "ppr.vendorSkillInfo";
	}
	
	@RequestMapping(value="UserServlet/createVendor.html", method=RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request){
		return getRecordPage(request, null);
	}
	
	@RequestMapping(value="UserServlet/createVendor.html", method=RequestMethod.POST)
	public ModelAndView doPost(@ModelAttribute("FormVendor") ModelVendorInfo form, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, Locale locale){
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		PageMsg pageMsg = form.validate();
		
		if(pageMsg.isError() == false){
			boolean isSuccess = vendorService.create(form, accessTicket);
			if(isSuccess){
				ResponseUtil.setOneTimeMsg(request, "msg.request.creation.success", messageSource, locale);
				return new ModelAndView("redirect:/UserServlet/searchVendors.html");
			}
			pageMsg.setError("msg.request.creation.failed");
		}
		ResponseUtil.setPageMsg(request, pageMsg, messageSource, locale);
		return getRecordPage(request, form);
	}
	
	@RequestMapping(value="UserServlet/viewVendor.html")
	public ModelAndView viewVendor(@RequestParam("refId") String accessId, 
			HttpServletRequest request, Locale locale){
		String errorKey = "access.denied.get.invalid";
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		if(recordInfo != null){
			VoVendorInfo vo = vendorService.view(recordInfo.getKey());
			if(vo != null){
				vo.setAccessId(accessId);
				WebUtil.buildAccessId(vo.getAttachedFiles());
				
				AccessTicket accessTicket = SessionUtil.getTicket(request);
				request.setAttribute("isVendorEditAllowed", UserRoleUtil.isProcumentor(accessTicket));
				
				WebUtil.populate(comboService, request, "designationCol", BatchTableMapping.DESIGNATION);
				
				boolean isShowStatus = (AppConfigConst.GTEC_VENDOR_ID == vo.getRecordId()) ? false :  true;
				request.setAttribute("isShowStatus", isShowStatus);
				
				request.setAttribute(AppConfigConst.PAGE_DATA, vo);
				request.setAttribute(AppConfigConst.BODY_TITLE_KEY, vo.getName());
				return new ModelAndView("body.page.vendor.viewVendor");
			}
		}
		ResponseUtil.setOneTimeMsg(request, errorKey, true, messageSource, locale);
		return new ModelAndView("redirect:/UserServlet/searchVendors.html");
	}
	
	@RequestMapping(value="UserServlet/manageVendor.html", method=RequestMethod.GET)
	public ModelAndView updateVendor(@RequestParam("refId") String accessId, 
			HttpServletRequest request, Locale locale){
		String errorKey = "access.denied.get.invalid";
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		if(recordInfo != null){
			ModelVendorInfo form = vendorService.get(recordInfo.getKey());
			if(form != null){
				form.setAccessId(accessId);
				return getRecordPage(request, form);
			}
		}
		ResponseUtil.setOneTimeMsg(request, errorKey, true, messageSource, locale);
		return new ModelAndView("redirect:/UserServlet/searchVendors.html");
	}
	
	@RequestMapping(value="UserServlet/manageVendor.html", method=RequestMethod.POST)
	public ModelAndView updateVendor(@ModelAttribute("FormVendor") ModelVendorInfo form, BindingResult result,
			@RequestParam("refId") String accessId, HttpServletRequest request, HttpServletResponse response, Locale locale){
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		PageMsg pageMsg = form.validate();
		
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		if(recordInfo == null){
			pageMsg.setError("access.denied.get.invalid");
		}
		
		if(pageMsg.isError() == false){
			form.setRecordId(recordInfo.getKey());
			
			if(AppConfigConst.GTEC_VENDOR_ID == recordInfo.getKey()){
				form.setActive(true);
			}
			
			boolean isSuccess = vendorService.update(form, accessTicket);
			if(isSuccess){
				ResponseUtil.setOneTimeMsg(request, "msg.request.creation.success", messageSource, locale);
				return new ModelAndView("redirect:/UserServlet/searchVendors.html");
			}
			pageMsg.setError("msg.request.creation.failed");
		}
		ResponseUtil.setPageMsg(request, pageMsg, messageSource, locale);
		return getRecordPage(request, form);
	}

	/*
	 * Below are the helper methods of this controller
	 */
	private ModelAndView getRecordPage(HttpServletRequest request, ModelVendorInfo form) {
		if(form == null){
			form = new ModelVendorInfo();
			form.setPersonCol(new ArrayList<PersonInfo>());
			PersonInfo person = new PersonInfo();
			person.setDefaultContact(true);
			form.getPersonCol().add(person);
		}
		
		String submitUlPath = "createVendor" + AppConfigConst.ACTION_SUFFIX;
		if(form.isNewRecord() == false){
			submitUlPath = "manageVendor" + AppConfigConst.ACTION_SUFFIX + "?refId=" + form.getAccessId();
		}
		
		WebUtil.populate(comboService, request, "designationCol", BatchTableMapping.DESIGNATION);
		
		boolean isShowStatus = form.isNewRecord() ? false : (AppConfigConst.GTEC_VENDOR_ID == form.getRecordId()) ? false :  true;
		
		request.setAttribute("isShowStatus", isShowStatus);
		request.setAttribute("submitUlPath", submitUlPath);
		request.setAttribute(AppConfigConst.PAGE_DATA, form);
		WebUtil.populate(comboService, request, "skillCol", BatchTableMapping.SERVICE_SKILL);
		String bodyTitle = form.isNewRecord() ? "Create Vendor" : "Update Vendor";
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, bodyTitle);
		
		return new ModelAndView("body.page.vendor.manageVendor");
	}
	
	@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod(){
	    return "fallback method";
	}
}
