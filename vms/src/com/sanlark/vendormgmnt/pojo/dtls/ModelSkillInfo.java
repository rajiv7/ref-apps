/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Teenam
 * @Version    : 1.0.1
 * @CreateDate : Jul 7, 2015
 */
package com.sanlark.vendormgmnt.pojo.dtls;

import com.sanlark.lib.web.pojo.BaseForm;

public class ModelSkillInfo extends BaseForm{
	private static final long serialVersionUID = 1L;

	private String[] accessId;
	private String[] skillTitle;
	private String[] desc;
	private boolean[] active;
	private boolean[] deleted;


	@Override
	public void doValidate() {
		
	}


	/**
	 * @return the recordId
	 */
	public String[] getAccessId() {
		return accessId;
	}


	/**
	 * @param recordId the recordId to set
	 */
	public void setAccessId(String[] accessId) {
		this.accessId = accessId;
	}


	/**
	 * @return the skillTitle
	 */
	public String[] getSkillTitle() {
		return skillTitle;
	}


	/**
	 * @param skillTitle the skillTitle to set
	 */
	public void setSkillTitle(String[] skillTitle) {
		this.skillTitle = skillTitle;
	}


	/**
	 * @return the desc
	 */
	public String[] getDesc() {
		return desc;
	}


	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String[] desc) {
		this.desc = desc;
	}


	/**
	 * @return the active
	 */
	public boolean[] getActive() {
		return active;
	}


	/**
	 * @param active the active to set
	 */
	public void setActive(boolean[] active) {
		this.active = active;
	}


	/**
	 * @return the deleted
	 */
	public boolean[] getDeleted() {
		return deleted;
	}


	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean[] deleted) {
		this.deleted = deleted;
	}

}
