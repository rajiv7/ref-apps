/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 08-Jul-2015 
 */
package com.sanlark.lib.core.datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SingleValRowMapper<O> implements RowMapper<O>{

	@SuppressWarnings("unchecked")
	@Override
	public O mapRow(ResultSet rs, int index)throws SQLException {
		return (O) rs.getObject(1);
	}
}
