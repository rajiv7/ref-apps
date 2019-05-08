package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.UserRole;
import com.sanlark.lib.web.util.EnumUtil;

public class AccessTicketRowMapper implements RowMapper<AccessTicket> {

	@Override
	public AccessTicket mapRow(ResultSet rs, int index) throws SQLException {
		AccessTicket accessTicket = new AccessTicket();
		accessTicket.setRecordId(rs.getLong(1));
		accessTicket.setName(rs.getString(2));
		
		UserRole userRole = EnumUtil.get(UserRole.class, rs.getInt(3));
		accessTicket.setUserRole(userRole);
		
		accessTicket.setActive(rs.getBoolean(4));
		
		return accessTicket;
	}
}
