/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 26-Jun-2015 
 */
package com.sanlark.lib.core.datalayer;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sanlark.lib.core.util.CommonUtil;

public class DaoHelper {
	public static <T> T getObject(JdbcTemplate jdbcTemplate, String sql, RowMapper<T> rowMapper, Object... args){
    	List<T> col = jdbcTemplate.query(sql, rowMapper, args);
		if(CommonUtil.hasElement(col)){
			return col.get(0);
		}
		return null;
    }
}
