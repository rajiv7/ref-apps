/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.vo.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VoVendorContact implements Serializable{
	private final Long vendorId;
	private String name;
	private String designation;
	private String email;
	private String contactNo;
	private boolean active;
	
	public VoVendorContact(Long vendorId){
		this.vendorId = vendorId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getVendorId() {
		return vendorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoVendorContact other = (VoVendorContact) obj;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}
}
