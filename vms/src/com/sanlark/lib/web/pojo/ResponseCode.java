/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.pojo;

public enum ResponseCode {
	TIMEOUT(-1),
	FAILED(0),
	SUCCESS(1, false),
	REDIRECT(2),
	;
	private ResponseCode(int val){
		this(val, true);
	}
	
	private ResponseCode(int val, boolean isError){
		this.id = val;
		this.isError = isError;
	}
	
	public final int id;
	public final boolean isError;
}
