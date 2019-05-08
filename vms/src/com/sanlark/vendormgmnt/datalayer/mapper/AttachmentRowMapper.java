/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Teenam
 * @Version    : 1.0.1
 * @CreateDate : Jul 13, 2015
 */
package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.FileType;
import com.sanlark.lib.web.util.EnumUtil;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;
public class AttachmentRowMapper implements RowMapper<VoAttachmentDtls> {

	private boolean isLoadBlob;

	public AttachmentRowMapper(boolean isLoadBlob) {
		this.isLoadBlob = isLoadBlob;
	}

	@Override
	public VoAttachmentDtls mapRow(ResultSet rs, int param)
			throws SQLException {
		// a.record_id, a.file_name, a.file_type, a.file_data
		
		VoAttachmentDtls obj = new VoAttachmentDtls();
		
		obj.setRecordId(rs.getLong(1));
		obj.setFileName(rs.getString(2));
		
		FileType fileType = EnumUtil.get(FileType.class, rs.getInt(3));
		obj.setFileType(fileType);
		
		obj.setFileSize(CommonUtil.toByteCount(rs.getLong(4)));
		
		if(isLoadBlob){
			obj.setFileByteData(rs.getBytes(5));
		}
		return obj;
	}

}
