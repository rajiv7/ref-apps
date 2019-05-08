/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 03-Jul-2015 
 */
package com.sanlark.lib.web.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageMsg implements Serializable{
	private String msg;
	private boolean error;
	private String element;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setSuccessMsg(String successMsg) {
		this.msg = successMsg;
		this.error = false;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public void setError(String errorMsg){
		setError(errorMsg, null);
	}
	public void setError(String errorMsg, String elemnt){
		this.msg = errorMsg;
		this.error = true;
		this.element = elemnt;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	
	@Override
	public String toString(){
		return "{error:" + error + ", msg:" + msg + ", element:" + element + "}";
	}
}
