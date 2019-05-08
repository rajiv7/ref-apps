/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.vo.common;

import java.util.List;

import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;
import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;

public class VoVendorInfo extends BaseVo{
	private static final long serialVersionUID = 5443002878409951L;
	
	private String name;
	private String desc;
	private String logo;
	private List<String> skillCol;
	private List<PersonInfo> personCol;
	
	private boolean active;

	private List<VoAttachmentDtls> attachedFiles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getSkillCol() {
		return skillCol;
	}
	public void setSkillCol(List<String> skillCol) {
		this.skillCol = skillCol;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the attachedFiles
	 */
	public List<VoAttachmentDtls> getAttachedFiles() {
		return attachedFiles;
	}
	/**
	 * @param attachedFiles the attachedFiles to set
	 */
	public void setAttachedFiles(List<VoAttachmentDtls> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
	public List<PersonInfo> getPersonCol() {
		return personCol;
	}
	public void setPersonCol(List<PersonInfo> personCol) {
		this.personCol = personCol;
	}
}
