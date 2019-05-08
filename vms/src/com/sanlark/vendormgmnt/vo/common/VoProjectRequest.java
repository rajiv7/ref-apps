/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 02-Jul-2015 
 */
package com.sanlark.vendormgmnt.vo.common;

import java.util.List;

import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;

@SuppressWarnings("serial")
public class VoProjectRequest extends BaseVo{
	private String title;
	private String desc;
	private String startDate;
	private String duration;
	private String location;
	private String category;

	private RequestStatus requestStatus;
	
	// TODO -- GANESH -- Verify and later remove the below 2 attributes
	private String status;
	private String statusCaption;
	
	
	
	private boolean includeAttachment;
	
	private Long createdById;
	
	private List<String> skillCol;
	
	private String createdDate;
	
	private List<VoAttachmentDtls> attachedFiles;

	private boolean editAllowed;
	
	private List<VoVendorSkillCount> matchedVendors;
	
	private String selectedVendor;
	
	public boolean isEditAllowed() {
		return editAllowed;
	}
	public void setEditAllowed(boolean editAllowed) {
		this.editAllowed = editAllowed;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param durationTitle
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getDuration(){
		return duration;
	}
	public boolean isIncludeAttachment() {
		return includeAttachment;
	}
	public void setIncludeAttachment(boolean includeAttachment) {
		this.includeAttachment = includeAttachment;
	}
	public Long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	public List<String> getSkillCol() {
		return skillCol;
	}
	public void setSkillCol(List<String> skillCol) {
		this.skillCol = skillCol;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public List<VoAttachmentDtls> getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(List<VoAttachmentDtls> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
	public String getStatusCaption() {
		return statusCaption;
	}
	public void setStatusCaption(String statusCaption) {
		this.statusCaption = statusCaption;
	}
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	public List<VoVendorSkillCount> getMatchedVendors() {
		return matchedVendors;
	}
	public void setMatchedVendors(List<VoVendorSkillCount> matchedVendors) {
		this.matchedVendors = matchedVendors;
	}
	public String getSelectedVendor() {
		return selectedVendor;
	}
	public void setSelectedVendor(String selectedVendor) {
		this.selectedVendor = selectedVendor;
	}
}
