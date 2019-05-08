/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.vo.common;

import java.io.Serializable;

import com.sanlark.lib.web.util.WebUtil;

@SuppressWarnings("serial")
public class BaseVo implements Serializable{
	
	private long recordId;
	
	/**
	 * A combination of recordId and createdById/some key
	 */
	private String accessId;
	
	@Override
	public final String toString(){
		return WebUtil.toJson(this);
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
}
