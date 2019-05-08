/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 15, 2015
 */
package com.sanlark.vendormgmnt.logicLayer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sanlark.vendormgmnt.datalayer.IDownloadDao;
import com.sanlark.vendormgmnt.logicLayer.IDownloadService;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;

public class DownloadServiceImpl implements IDownloadService {
	
	@Autowired
	@Qualifier("downloadDao")
	private IDownloadDao dao;
	
	@Override
	public VoAttachmentDtls get(long recordId) {
		return dao.get(recordId);
	}
}
