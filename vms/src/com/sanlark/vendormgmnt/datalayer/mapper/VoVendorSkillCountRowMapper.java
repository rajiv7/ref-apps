package com.sanlark.vendormgmnt.datalayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.vendormgmnt.vo.common.VoVendorSkillCount;

public class VoVendorSkillCountRowMapper implements RowMapper<VoVendorSkillCount>{

	@Override
	public VoVendorSkillCount mapRow(ResultSet rs, int index)
			throws SQLException {
		VoVendorSkillCount vo = new VoVendorSkillCount();
		vo.setRecordId(rs.getLong(1));
		vo.setName(rs.getString(2));
		vo.setMatchedCount(rs.getInt(3));
		return vo;
	}
}
