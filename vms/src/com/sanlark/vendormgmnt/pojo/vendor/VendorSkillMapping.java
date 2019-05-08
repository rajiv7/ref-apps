/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.vendor;

public class VendorSkillMapping {
	private long recordId;
	private long vendorId;
	private long skillId;
	
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
}
