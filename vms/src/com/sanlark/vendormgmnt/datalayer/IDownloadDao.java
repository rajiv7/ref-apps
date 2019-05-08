/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 15, 2015
 */
package com.sanlark.vendormgmnt.datalayer;

import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;

public interface IDownloadDao {
	VoAttachmentDtls get(long recordId) ;
}
