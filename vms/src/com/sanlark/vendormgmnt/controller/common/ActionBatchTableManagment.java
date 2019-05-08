/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 14, 2015
 */
package com.sanlark.vendormgmnt.controller.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.ResponseUtil;
import com.sanlark.lib.web.util.SessionUtil;
import com.sanlark.vendormgmnt.datalayer.BatchTableMapping;
import com.sanlark.vendormgmnt.form.FormBatchTable;
import com.sanlark.vendormgmnt.logicLayer.IBatchService;
import com.sanlark.vendormgmnt.pojo.common.BatchTableInfo;

@Controller
public class ActionBatchTableManagment {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IBatchService batchService;
	
	@RequestMapping(value="UserServlet/Manage/{urlPath}.html", method=RequestMethod.GET)
	public ModelAndView getSkills(@PathVariable String urlPath, HttpServletRequest request) {
		BatchTableMapping tableMapping = BatchTableMapping.get(urlPath);
		if(tableMapping == null){
			return new ModelAndView(AppConfigConst.USER_HOME_VIEW_PATH);
		}
		FormBatchTable form = new FormBatchTable();
		List<BatchTableInfo> batchCol = batchService.getList(tableMapping);
		if(CommonUtil.hasElement(batchCol) == false){
			batchCol = new ArrayList<BatchTableInfo>();
			
			BatchTableInfo b = new BatchTableInfo();
			b.setActive(true);
			batchCol.add(b);
			request.setAttribute("isEmptyRows", true);
		}
		form.setBatchCol(batchCol);
		
		request.setAttribute("formActionPath", urlPath + AppConfigConst.ACTION_SUFFIX);
		request.setAttribute(AppConfigConst.PAGE_DATA, form);
		request.setAttribute(AppConfigConst.BODY_TITLE_KEY, tableMapping.bodyTitle);
		return new ModelAndView("body.page.common.manageBatch");
	}
	
	@RequestMapping(value="UserServlet/Manage/{urlPath}.html", method=RequestMethod.POST)
	public ModelAndView saveSkills(@ModelAttribute("frmBatchTable")FormBatchTable form, 
			BindingResult result, @PathVariable String urlPath, HttpServletRequest request, Locale locale) {
		BatchTableMapping tableMapping = BatchTableMapping.get(urlPath);
		if(tableMapping == null){
			return new ModelAndView(AppConfigConst.USER_HOME_VIEW_PATH);
		}
		List<BatchTableInfo> batchCol = form.getBatchCol();
		AccessTicket accessTicket = SessionUtil.getTicket(request);
		batchService.update(tableMapping, batchCol, accessTicket);
		ResponseUtil.setOneTimeMsg(request, "msg.request.creation.success", messageSource, locale);
		return new ModelAndView("redirect:/UserServlet/Manage/" + urlPath + AppConfigConst.ACTION_SUFFIX);
	}
}
