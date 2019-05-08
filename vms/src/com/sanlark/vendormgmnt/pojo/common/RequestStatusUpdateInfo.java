/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 17, 2015
 */
package com.sanlark.vendormgmnt.pojo.common;

import java.io.Serializable;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;

@SuppressWarnings("serial")
public class RequestStatusUpdateInfo implements Serializable{
	private final Long recordId;
	private final AccessTicket accessTicket;
	private final RequestStatus newStatus;
	
	private String txnDate;
	private Long vendorId;
	private String po;
	
	public RequestStatusUpdateInfo(Long recordId, AccessTicket accessTicket, 
			RequestStatus newStatus){
		this.recordId = recordId;
		this.accessTicket = accessTicket;
		this.newStatus = newStatus;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public Long getRecordId() {
		return recordId;
	}

	public AccessTicket getAccessTicket() {
		return accessTicket;
	}

	public RequestStatus getNewStatus() {
		return newStatus;
	}
}
