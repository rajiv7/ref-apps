/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 15, 2015
 */
package com.sanlark.vendormgmnt.controller.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanlark.lib.web.pojo.FileType;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.lib.web.util.WebUtil;
import com.sanlark.vendormgmnt.logicLayer.IDownloadService;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;

@Controller
public class ActionFileDownloader {
	
	@Autowired
	private IDownloadService downloadService;
	
	@RequestMapping(value = "UserServlet/downloadFiles.html")
	public void download(@RequestParam("refId") String accessId, 
			HttpServletRequest request, HttpServletResponse response) {
		KeyValPair<Long, String> recordInfo = WebUtil.getRecordInfo(accessId);
		if (recordInfo != null) {
			VoAttachmentDtls vo = downloadService.get(recordInfo.getKey());
			if (vo != null) {
				response.setContentType(vo.getFileType().getContentType());
				response.setContentType(FileType.get(vo.getFileName()).getContentType());
		
				String headerValue = String.format("attachment; filename=\"%s\"",vo.getFileName());
				response.setHeader("Content-Disposition", headerValue);
		
				try {
					FileCopyUtils.copy(vo.getFileByteData(),response.getOutputStream());
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// TODO -- RAJIV Handle Error
	}
}
