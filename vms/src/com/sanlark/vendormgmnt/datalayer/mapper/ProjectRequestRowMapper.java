/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 03-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.lib.core.util.DateUtil;
import com.sanlark.lib.web.util.EnumUtil;
import com.sanlark.vendormgmnt.pojo.dtls.ModelProjectRequest;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;

public class ProjectRequestRowMapper implements RowMapper<ModelProjectRequest>{

	@Override
	public ModelProjectRequest mapRow(ResultSet rs, int index) throws SQLException {
		ModelProjectRequest obj = new ModelProjectRequest();
		obj.setRecordId(rs.getLong(1));
		obj.setTitle(rs.getString(2));
		obj.setDesc(rs.getString(3));
		obj.setDurationId(rs.getInt(4));
		obj.setLocationId(rs.getInt(5));
		obj.setCategoryId(rs.getInt(6));
		obj.setIncludeAttachment(rs.getBoolean(7));

		obj.setStartDate(DateUtil.getUiDate(rs.getDate(8).getTime()));
		obj.setCloseDate(DateUtil.getUiDate(rs.getDate(9)));
		obj.setDeliveryDate(DateUtil.getUiDate(rs.getDate(10)));
		
		obj.setStatusId(EnumUtil.get(RequestStatus.class, rs.getInt(11)));
		
		obj.setVendorId(rs.getInt(12));
		obj.setPoDtls(rs.getString(13));
		obj.setCreatedDate(rs.getDate(14).getTime());
		obj.setCreatedById(rs.getLong(15));
		
		Date date = rs.getDate(16);
		if(date != null){
			obj.setUpdateDate(date.getTime());
			obj.setUpdatedById(rs.getLong(17));
		}
		return obj;
	}
}
