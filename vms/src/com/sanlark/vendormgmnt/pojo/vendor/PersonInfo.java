/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.vendor;

import com.sanlark.lib.web.pojo.BasePojo;

public class PersonInfo extends BasePojo{
	private static final long serialVersionUID = 8447566526496411213L;
	private Long vendorId;
	private String name;
	private int designationId;
	private String profileImage;
	
	private String email;
	private String contacts;
	
	private boolean defaultContact;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public boolean isDefaultContact() {
		return defaultContact;
	}
	public void setDefaultContact(boolean defaultContact) {
		this.defaultContact = defaultContact;
	}
}
