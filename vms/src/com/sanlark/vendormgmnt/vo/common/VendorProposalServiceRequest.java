package com.sanlark.vendormgmnt.vo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sanlark.lib.core.util.CommonUtil;

@SuppressWarnings("serial")
public class VendorProposalServiceRequest implements Serializable{
	private String error;
	
	private final Long requestId;
	
	private final List<Long> requestVendorCol;
	
	private final List<Long> responseVendorCol;
	
	public VendorProposalServiceRequest(Long requestId, List<Long> requestVendorCol) {
		this.requestId = requestId;
		this.requestVendorCol = requestVendorCol;
		this.responseVendorCol = new ArrayList<Long>();
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Long getRequestId() {
		return requestId;
	}
	public List<Long> getRequestVendorCol() {
		return requestVendorCol;
	}
	public List<Long> getResponseVendorCol() {
		return responseVendorCol;
	}
	public boolean isError() {
		return !CommonUtil.isEmpty(error);
	}
}
