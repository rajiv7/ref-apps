/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.core.datalayer;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDaoService{
	
	protected JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public <I,O> List<O> getValueMap(IMappingTable mappingTable, I keyClmValue){
		StringBuilder query = new StringBuilder("select ");
		query.append(mappingTable.getValueClmName());
		query.append(" from " + mappingTable.getTableName());
		query.append(" where " + mappingTable.getKeyClmName() + "=?");
		return getMap(query.toString(), keyClmValue);
	}
    
    public <I,O> List<O> getMap(String query, I keyClmValue){
    	SingleValRowMapper<O> rowMapper = new SingleValRowMapper<O>();
		return jdbcTemplate.query(query.toString(), rowMapper, keyClmValue);
    }
    
    public <O> List<O> getMap(String query){
    	SingleValRowMapper<O> rowMapper = new SingleValRowMapper<O>();
		return jdbcTemplate.query(query.toString(), rowMapper);
    }
}
