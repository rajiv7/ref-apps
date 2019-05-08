/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.vendormgmnt.vo.common.VoVendorInfo;

public class VoVendorInfoRowMapper implements RowMapper<VoVendorInfo>{
	private boolean isLoadFullInfo;
	
	public VoVendorInfoRowMapper(boolean isLoadFullInfo){
		this.isLoadFullInfo = isLoadFullInfo;
	}
	
	@Override
	public VoVendorInfo mapRow(ResultSet rs, int index) throws SQLException {
		VoVendorInfo obj = new VoVendorInfo();
		obj.setRecordId(rs.getLong(1));
		obj.setName(rs.getString(2));
		obj.setLogo(rs.getString(3));
		obj.setActive(rs.getBoolean(4));
		
		if(isLoadFullInfo){
			obj.setDesc(rs.getString(5));
		}
		return obj;
	}
}
