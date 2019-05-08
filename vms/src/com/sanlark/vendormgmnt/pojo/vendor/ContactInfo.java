/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.vendor;

import com.sanlark.lib.web.pojo.BasePojo;

public class ContactInfo extends BasePojo{
	private static final long serialVersionUID = 3606746055587156522L;

	private int personId;
	private String email;
	private String contactNo;
	
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
	
	@Override
	public String toString(){
		return "{" + email + ", " + contactNo + "}";
	}
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
}
