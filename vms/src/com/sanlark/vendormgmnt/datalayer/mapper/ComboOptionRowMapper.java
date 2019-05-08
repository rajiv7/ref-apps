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

import com.sanlark.lib.web.pojo.ComboOption;

public class ComboOptionRowMapper implements RowMapper<ComboOption>{
	
	@Override
	public ComboOption mapRow(ResultSet rs, int index) throws SQLException {
		ComboOption obj = new ComboOption();
		obj.setId(rs.getLong(1));
		obj.setTitle(rs.getString(2));
		obj.setActive(rs.getBoolean(3));
		return obj;
	}
}
