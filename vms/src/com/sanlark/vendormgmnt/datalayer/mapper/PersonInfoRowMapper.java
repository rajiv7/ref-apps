/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 15, 2015
 */
package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;

public class PersonInfoRowMapper implements RowMapper<PersonInfo>{

	@Override
	public PersonInfo mapRow(ResultSet rs, int index) throws SQLException {
		PersonInfo obj = new PersonInfo();
		obj.setRecordId(rs.getLong(1));
		obj.setVendorId(rs.getLong(2));
		obj.setName(rs.getString(3));
		obj.setDesignationId(rs.getInt(4));
		obj.setEmail(rs.getString(5));
		obj.setContacts(rs.getString(6));
		obj.setDefaultContact(rs.getBoolean(7));
		
		return obj;
	}
}
