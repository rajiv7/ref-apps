package com.sanlark.lib.web.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageData implements Serializable {
	private Object data;
	private boolean error;
	
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
	public void setErrorMsg(String errMsg) {
		this.error = true;
		this.data = errMsg;
	}
}
