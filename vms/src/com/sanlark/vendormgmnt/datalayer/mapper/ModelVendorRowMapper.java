package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.vendormgmnt.pojo.vendor.ModelVendorInfo;

public class ModelVendorRowMapper implements RowMapper<ModelVendorInfo>{

	@Override
	public ModelVendorInfo mapRow(ResultSet rs, int index) throws SQLException {
		ModelVendorInfo obj = new ModelVendorInfo();
		obj.setRecordId(rs.getLong(1));
		obj.setCompanyName(rs.getString(2));
		obj.setLogo(rs.getString(3));
		obj.setActive(rs.getBoolean(4));
		obj.setDesc(rs.getString(5));
		
		return obj;
	}
}
