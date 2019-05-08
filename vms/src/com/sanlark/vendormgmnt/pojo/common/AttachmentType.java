/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.common;

public enum AttachmentType {
	VENDOR(1),
	REQUEST(2)
	;
	
	private AttachmentType(int id){
		this.id = id;
	}
	public final int id;
}
