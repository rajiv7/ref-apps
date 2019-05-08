/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 13-Jul-2015 
 */

package com.sanlark.lib.web.pojo;

import com.sanlark.lib.core.util.CommonUtil;

public enum FileType implements IEnumComboLoader{
	TEXT(1, "text/html", "txt"),
	IMAGE(2, "image/jpeg", "jpg", "jpeg", "bmp", "png"),
	PDF(3, "application/pdf", "pdf"),
	DOC(4, "application/vnd-word", "doc", "docx"),
	EXCEL(5, "application/vnd-xls", "xls", "xlsx"),
	PPT(6, "application/vnd-xls", "ppt", "pptx"),
	JAR(7, "application/java-archive", "jar"),
	APK(8, "application/vnd.android.package-archive", "apk"),
	;
	
	private FileType(int id, String contentType, String... allowedSuffix){
		this.id = id;
		this.contentType = contentType;
		this.allowedSuffix = allowedSuffix;
	}
	
	public final int id;
	public final String contentType;
	public final String[] allowedSuffix;
	
	public static boolean isAllowedType(String fileName){
		FileType type = get(fileName);
		return (type != null);
	}
	
	public static FileType get(String fileName){
		String[] file = splitExtension(fileName);
		if(file == null || file.length != 2){
			return null;
		}
		FileType obj = null;
		outer :
		for(FileType type : values()){
			for(String suffix : type.allowedSuffix){
				if(suffix.equals(file[1])){
					obj = type;
					break outer;
				}
			}
		}
		return obj;
	}
	
	public static String[] splitExtension(String fileName){
		fileName = CommonUtil.trim(fileName);
		if(CommonUtil.isEmpty(fileName)){
			return null;
		}
		String[] arr = new String[2];
		int tmpIndex = fileName.lastIndexOf(".");
		arr[0] = fileName.substring(0, tmpIndex);
		arr[1] = fileName.substring(tmpIndex+1).toLowerCase();
		return arr;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return toString();
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
}
