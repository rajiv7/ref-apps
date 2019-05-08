/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.core.datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.sanlark.lib.web.pojo.ComboOption;

public class MappingTableDaoLoaderImpl extends AbstractDaoService implements IMappingTableDaoLoader{

	@Override
	public long load(IMappingTable mappingTable, String keyClmValue){
		List<Long> col = getValueMap(mappingTable, keyClmValue);
		if(col != null && col.size()>0){
			return col.get(0);
		}
		return -1;
	}
	
	@Override
	public List<ComboOption> load(IMappingTable mappingTable) {
		StringBuilder query = new StringBuilder("select ");
		query.append(mappingTable.getKeyClmName() + ", " + mappingTable.getValueClmName());
		query.append(" from " + mappingTable.getTableName());
		
		if(mappingTable.isFilterInactive()){
			query.append(" where status_id=1");
		}
		
		ComboOptionRowMapper rowMapper = new ComboOptionRowMapper(mappingTable);
		
		List<ComboOption> col = jdbcTemplate.query(query.toString(), rowMapper);
		
		return col;
	}
	
	class ComboOptionRowMapper implements RowMapper<ComboOption>{
		IMappingTable mappingTable;
		
		public ComboOptionRowMapper(IMappingTable mappingTable){
			this.mappingTable = mappingTable;
		}
		
		@Override
		public ComboOption mapRow(ResultSet rs, int index)
				throws SQLException {
			ComboOption obj = new ComboOption();
			obj.setId(rs.getLong(mappingTable.getKeyClmName()));
			obj.setTitle(rs.getString(mappingTable.getValueClmName()));
			return obj;
		}
	}
}
