/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 15, 2015
 */
package com.sanlark.vendormgmnt.datalayer;

import com.sanlark.lib.core.datalayer.AbstractDaoService;
import com.sanlark.lib.core.datalayer.DaoHelper;
import com.sanlark.vendormgmnt.datalayer.mapper.AttachmentRowMapper;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;

public class DownloadDaoImpl extends AbstractDaoService implements IDownloadDao{

	@Override
	public VoAttachmentDtls get(long recordId) {
		return DaoHelper.getObject(jdbcTemplate, Queries.GET_ATTACHMENT_DATA, 
				new AttachmentRowMapper(true), recordId);
	}
}
