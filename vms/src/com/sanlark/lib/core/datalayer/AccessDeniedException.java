/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 03-Jul-2015 
 */
package com.sanlark.lib.core.datalayer;

@SuppressWarnings("serial")
public class AccessDeniedException extends Exception{

	public AccessDeniedException(){
		this("Access Denied Exception");
	}
	public AccessDeniedException(String message){
		super(message);
	}
	
	public String getErrorKey() {
		return "access.denied.get.record";
	}
}
