/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.vendor;

import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.FormModel;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;

public class ModelVendorInfo extends FormModel {
	private static final long serialVersionUID = 8695716237477551171L;
	
	private String companyName;
	private String desc;
	private String logo;
	
	private List<PersonInfo> personCol;
	
	private int[] skills;
	
	private MultipartFile[]  attachments;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<PersonInfo> getPersonCol() {
		return personCol;
	}

	public void setPersonCol(List<PersonInfo> personCol) {
		this.personCol = personCol;
	}
	
	public int[] getSkills() {
		return skills;
	}
	public void setSkills(int[] skills) {
		this.skills = skills;
	}
	
	public MultipartFile[] getAttachments() {
		return attachments;
	}
	public void setAttachments(MultipartFile[] attachments) {
		this.attachments = attachments;
	}
	
	@Override
	protected void doValidate() {
		if(CommonUtil.isEmpty(companyName)){
			pageMsg.setError("validation.invalid.vendor.companyName", "companyName");
		}else if(skills == null || skills.length == 0){
			pageMsg.setError("validation.invalid.skills", "skills");
		}
		// TODO -- TEENAM -- Validate. Call AttachmentType.isAllowed(fileName) in a loop
		
		// Validate and remove empty person rows
		if(personCol != null){
			for (Iterator<PersonInfo> iterator = personCol.iterator(); iterator.hasNext();) {
				PersonInfo p = iterator.next();
				if(CommonUtil.isEmpty(p.getName())){
			        // Remove the current element from the iterator and the list.
			        iterator.remove();
			    }
			}
		}
	}
	
	private List<VoAttachmentDtls> attachedFiles;
		
	private List<String> deletedFiles;

	public List<String> getDeletedFiles() {
		return deletedFiles;
	}
	public void setDeletedFiles(List<String> deletedFiles) {
		this.deletedFiles = deletedFiles;
	}
	public List<VoAttachmentDtls> getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(List<VoAttachmentDtls> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
}
