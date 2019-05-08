/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 7, 2015
 */
package com.sanlark.vendormgmnt.datalayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.sanlark.lib.core.datalayer.AbstractDaoService;
import com.sanlark.lib.core.datalayer.IBatchDaoService;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.vendormgmnt.datalayer.mapper.BatchTableRowMapper;
import com.sanlark.vendormgmnt.pojo.common.BatchTableInfo;

@Service
public class BatchDaoImpl extends AbstractDaoService implements IBatchDaoService<BatchTableInfo> {
	
	@Override
	@Transactional
	public void update(String tableName, List<BatchTableInfo> insertCol, 
			List<BatchTableInfo> updateCol, Long userRefId)throws TransactionException {
		int totalRequiredUpdate = insertCol.size() + updateCol.size();
		
		if(totalRequiredUpdate == 0){
			return;
		}
		
		int totalUpdated = (insertCol.size() > 0) ? createBatch(tableName, insertCol, userRefId) : 0;
		totalUpdated += (updateCol.size() > 0) ? updateBatch(tableName, updateCol, userRefId) : 0;
		
		if(totalUpdated != totalRequiredUpdate){
			throw new TransactionException();
		}
	}
	
	private int createBatch(String tableName, final List<BatchTableInfo> insertCol, final Long userRefId){
		int[] rtArr = jdbcTemplate.batchUpdate(getInsertQuery(tableName), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BatchTableInfo obj = insertCol.get(i);
				
				ps.setString(1, obj.getTitle());
				ps.setString(2, obj.getDesc());
				ps.setLong(3, userRefId);
			}
			@Override
			public int getBatchSize() {
				return insertCol.size();
			}
		});
		return rtArr.length;
	}

	private int updateBatch(String tableName, final List<BatchTableInfo> updateCol, final Long userRefId){
		int[] rtArr = jdbcTemplate.batchUpdate(getUpdateQuery(tableName), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BatchTableInfo obj = updateCol.get(i);
				
				ps.setString(1, obj.getTitle());
				ps.setString(2, obj.getDesc());
				ps.setBoolean(3, obj.isActive());
				ps.setLong(4, userRefId);
				ps.setLong(5, obj.getRecordId());
			}
			@Override
			public int getBatchSize() {
				return updateCol.size();
			}
		});
		return rtArr.length;
	}

	@Override
	public List<BatchTableInfo> list(String tableName) {
		return jdbcTemplate.query(getListQuery(tableName), new BatchTableRowMapper());
	}
	
	public String getInsertQuery(String tableName){
		return "insert into "+ tableName +" (title,description, created_by_id) values(?,?,?)";
	}
	public String getUpdateQuery(String tableName){
		return "update "+ tableName +" set title=?, description=?, status_id=?, updated_by_id=? where record_id=?";
	}
	public String getListQuery(String tableName){
		return "select record_id, title, description, status_id, created_date, created_by_id from " + tableName;
	}
}
