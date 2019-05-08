/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.common;

import com.sanlark.lib.web.pojo.FileType;
import com.sanlark.vendormgmnt.vo.common.BaseVo;

public class VoAttachmentDtls extends BaseVo{
	private static final long serialVersionUID = -4464086004665139966L;
	
	private String fileName;
	private String filePath; // Physical location of the file
	private AttachmentType typeId;
	private FileType fileType;
	private String fileSize;
	
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	private byte[] fileByteData;
	
	public byte[] getFileByteData() {
		return fileByteData;
	}
	public void setFileByteData(byte[] fileByteData) {
		this.fileByteData = fileByteData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public AttachmentType getTypeId() {
		return typeId;
	}
	public void setTypeId(AttachmentType typeId) {
		this.typeId = typeId;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
