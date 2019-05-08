/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.common;

import com.sanlark.lib.web.pojo.BasePojo;

public class InhousePersonInfo extends BasePojo{
	private static final long serialVersionUID = 3646512442579756045L;
	private String ldapId;
	private InhousePersonStatus status;
	private InhousePersonType personTypeId;
	
	public InhousePersonInfo(){
		personTypeId = InhousePersonType.PROCUREMENTOR;
	}
	
	public String getLdapId() {
		return ldapId;
	}
	public void setLdapId(String ldapId) {
		this.ldapId = ldapId;
	}
	public InhousePersonStatus getStatus() {
		return status;
	}
	public void setStatus(InhousePersonStatus status) {
		this.status = status;
	}
	
	public InhousePersonType getPersonTypeId() {
		return personTypeId;
	}
	public void setPersonTypeId(InhousePersonType personTypeId) {
		this.personTypeId = personTypeId;
	}
	
	@Override
	public String toString(){
		return ldapId;
	}
}
