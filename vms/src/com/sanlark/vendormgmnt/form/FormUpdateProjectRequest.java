package com.sanlark.vendormgmnt.form;

import java.io.Serializable;

public class FormUpdateProjectRequest implements Serializable{
	private static final long serialVersionUID = -2004203839597465855L;
	
	private String refId;
	private int actionCmd;
	
	private String txnDate;
	
	private long vendorId;
	private String po;
	
	private boolean ajaxCall;

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public int getActionCmd() {
		return actionCmd;
	}

	public void setActionCmd(int actionCmd) {
		this.actionCmd = actionCmd;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public boolean isAjaxCall() {
		return ajaxCall;
	}

	public void setAjaxCall(boolean ajaxCall) {
		this.ajaxCall = ajaxCall;
	}
}
