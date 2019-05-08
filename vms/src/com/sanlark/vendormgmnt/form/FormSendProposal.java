package com.sanlark.vendormgmnt.form;

import java.util.List;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.BaseForm;

@SuppressWarnings("serial")
public class FormSendProposal extends BaseForm{
	
	private String refId;
	private List<String> vendors;
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public List<String> getVendors() {
		return vendors;
	}
	public void setVendors(List<String> vendors) {
		this.vendors = vendors;
	}
	@Override
	public void doValidate() {
		if(CommonUtil.hasElement(vendors) == false){
			pageMsg.setError("No vendor selected");
		}
	}
}
