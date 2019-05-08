/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.dtls;

import com.sanlark.lib.web.pojo.IEnumComboLoader;

public enum RequestStatus implements IEnumComboLoader{
	SUBMITTED_TO_GTEC(1, "Submitted", "label-default", "Pending for GTEC Response"), // Requester
	RE_SUBMITTED_TO_GTEC(9, "Re-submitted", "label-default", "Pending for GTEC Response"), // Requester
	
	ACCEPTED_BY_GTEC(2, "In Progress", "label-info disabled", "Accepted by GTEC"), // GTEC -- will update the close date) and auto Vendor selected as GTEC 
	DECLINED_BY_GTEC(3, "Declined by GTEC", "label-warning"), // GTEC/Auto -- Either declined or 48 hours period completes
	
	SUBMITTED_TO_VENDORS(4, "In process", "label-primary",  "Submitted to Vendors"), // Requester -- Sent mails to multiple vendors
	
	VENDOR_SELECTED(5, "In Progress", "label-info", "Vendor Selected"), // Procurement Person with vendor Id and (close date?)
	
	WORK_COMPLETED(6, "Completed", "label-success disabled"),// Procurement Personwith the deliver date and any other details
	
	CLOSED(7, "Closed", "label-success"),  // Requester --
	;
	
	private RequestStatus(int id, String title, String uiClass){
		this(id, title, uiClass, null);
	}
	
	private RequestStatus(int id, String title, String uiClass, String fullCaption){
		this.id = id;
		this.title = title;
		this.uiClass = uiClass;
		this.fullCaption = (fullCaption == null) ? title : title + " - " + fullCaption;
	}
	
	public final int id;
	public final String title;
	public final String uiClass;
	public final String fullCaption;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return title;
	}
}
