/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasePojo implements Serializable{
	private String accessId;
	
	private Long recordId;
	private boolean active;
	
	private long createdDate;
	private Long createdById;
	
	private long updateDate;
	private Long updatedById;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	
	@Override
	public String toString(){
		return "" + recordId;
	}
	
	public boolean isNewRecord(){
		return (recordId == null || recordId == 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (recordId ^ (recordId >>> 32));
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
		BasePojo other = (BasePojo) obj;
		if (recordId != other.recordId)
			return false;
		return true;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	public long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	
	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
}
