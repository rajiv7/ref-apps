/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.vendormgmnt.pojo.common;

import com.sanlark.lib.web.pojo.BasePojo;

public class BatchTableInfo extends BasePojo{
	private static final long serialVersionUID = -1608164120697039637L;
	private String title;
	private String desc;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString(){
		return "{" + getRecordId() + ", " + title + ", " + isActive() + "}"; 
	}
}
