/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 06-Jul-2015 
 */
package com.sanlark.vendormgmnt.form;

import java.io.Serializable;

public class FormFilterSearchRequest implements Serializable{
	private static final long serialVersionUID = 5015060362710635363L;
	
	private boolean chkTitle;
	private String txtTitle;
	
	private boolean chkDateRange;
	private String txtDateRange;
	
	public boolean isChkTitle() {
		return chkTitle;
	}
	public void setChkTitle(boolean chkTitle) {
		this.chkTitle = chkTitle;
	}
	public String getTxtTitle() {
		return txtTitle;
	}
	public void setTxtTitle(String txtTitle) {
		this.txtTitle = txtTitle;
	}
	public boolean isChkDateRange() {
		return chkDateRange;
	}
	public void setChkDateRange(boolean chkDateRange) {
		this.chkDateRange = chkDateRange;
	}
	public String getTxtDateRange() {
		return txtDateRange;
	}
	public void setTxtDateRange(String txtDateRange) {
		this.txtDateRange = txtDateRange;
	}
}
