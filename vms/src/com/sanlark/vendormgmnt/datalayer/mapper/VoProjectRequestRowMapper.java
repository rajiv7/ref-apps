/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 02-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.lib.core.util.DateUtil;
import com.sanlark.lib.web.util.EnumUtil;
import com.sanlark.vendormgmnt.pojo.common.ProjectDuration;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;
import com.sanlark.vendormgmnt.vo.common.VoProjectRequest;

public class VoProjectRequestRowMapper implements RowMapper<VoProjectRequest>{

	@Override
	public VoProjectRequest mapRow(ResultSet rs, int index)throws SQLException {
		VoProjectRequest obj = new VoProjectRequest();
		obj.setRecordId(rs.getLong(1));
		obj.setTitle(rs.getString(2));
		obj.setDesc(rs.getString(3));
		
		obj.setDuration(EnumUtil.getTitle(ProjectDuration.class, rs.getInt(4)));
		
		Date startDate = rs.getDate(5);
		obj.setStartDate(DateUtil.getUiDate(startDate.getTime()));
		
		RequestStatus requestStatus = EnumUtil.get(RequestStatus.class, rs.getInt(6));
		
		obj.setRequestStatus(requestStatus);
		obj.setStatus(requestStatus.title);
		obj.setStatusCaption(requestStatus.fullCaption);
		
		// 7 - Created date
		String createdDate = DateUtil.format(rs.getDate(7).getTime(), DateUtil.DEFAULT_DAY_PATTERN);
		obj.setCreatedDate(createdDate);
		
		obj.setLocation(rs.getString(8));
		obj.setCategory(rs.getString(9));
		
		obj.setIncludeAttachment(rs.getBoolean(10));
		obj.setCreatedById(rs.getLong(11));
		return obj;
	}
}
