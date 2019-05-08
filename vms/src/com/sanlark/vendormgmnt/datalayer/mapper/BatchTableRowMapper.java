/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 14, 2015
 */
package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.vendormgmnt.pojo.common.BatchTableInfo;

public class BatchTableRowMapper implements RowMapper<BatchTableInfo> {

	@Override
	public BatchTableInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		BatchTableInfo serviceLocation = new BatchTableInfo();
		
		serviceLocation.setRecordId(rs.getLong(1));
		serviceLocation.setTitle(rs.getString(2));
		serviceLocation.setDesc(rs.getString(3));
		serviceLocation.setActive(rs.getBoolean(4));
		
		return serviceLocation;
	}
}
