/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.pojo;

import java.io.Serializable;

public class JsonResponse implements Serializable{
	private static final long serialVersionUID = -2277689950053156173L;

	private Object data;
	private boolean error;
	private int statusId;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	@Override
	public String toString(){
		return "" + data;
	}
}
