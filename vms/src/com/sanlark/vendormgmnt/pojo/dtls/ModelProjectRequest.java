/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.dtls;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.core.util.DateUtil;
import com.sanlark.lib.web.pojo.FormModel;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;

public class ModelProjectRequest extends FormModel{
	private static final long serialVersionUID = -5890895864027101930L;
	
	private String title;
	private String desc;
	
	private int durationId;
	private int locationId;
	private int categoryId;

	private int[] skills;
	
	private boolean isIncludeAttachment;
	
	private String startDate;
	private String closeDate;
	private String deliveryDate;
	
	private RequestStatus statusId;
	
	private long vendorId;
	private String poDtls;
	
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
	public int getDurationId() {
		return durationId;
	}
	public void setDurationId(int durationId) {
		this.durationId = durationId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int[] getSkills() {
		return skills;
	}

	public void setSkills(int[] skills) {
		this.skills = skills;
	}
	public boolean isIncludeAttachment() {
		return isIncludeAttachment;
	}
	public void setIncludeAttachment(boolean isIncludeAttachment) {
		this.isIncludeAttachment = isIncludeAttachment;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public RequestStatus getStatusId() {
		return statusId;
	}
	public void setStatusId(RequestStatus statusId) {
		this.statusId = statusId;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getPoDtls() {
		return poDtls;
	}
	public void setPoDtls(String poDtls) {
		this.poDtls = poDtls;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	@Override
	public String toString(){
		return title;
	}

	@Override
	protected void doValidate() {
		// reset the start date
		if(DateUtil.isValidUiDate(startDate) == false){
			startDate = DateUtil.getUiDate();
		}
		
		if(CommonUtil.isEmpty(title)){
			pageMsg.setError("validation.invalid.request.title", "title");
		}else if(durationId == 0){
			pageMsg.setError("validation.invalid.duration", "durationId");
		}else if(locationId == 0){
			pageMsg.setError("validation.invalid.location", "locationId");
		}else if(categoryId == 0){
			pageMsg.setError("validation.invalid.category", "categoryId");
		}else if(skills == null || skills.length == 0){
			pageMsg.setError("validation.invalid.skills", "skills");
		}
		// TODO -- TEENAM -- Validate. Call AttachmentType.isAllowed(fileName) in a loop
	}
	
	private List<VoAttachmentDtls> attachedFiles;

	private List<String> deletedFiles;

	public List<String> getDeletedFiles() {
		return deletedFiles;
	}
	public void setDeletedFiles(List<String> deletedFiles) {
		this.deletedFiles = deletedFiles;
	}
	
	private MultipartFile[]  attachments;

	public MultipartFile[] getAttachments() {
		return attachments;
	}
	public void setAttachments(MultipartFile[] attachments) {
		this.attachments = attachments;
	}
	public List<VoAttachmentDtls> getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(List<VoAttachmentDtls> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
