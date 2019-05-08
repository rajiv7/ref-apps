/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.web.multipart.MultipartFile;

import com.sanlark.lib.core.datalayer.AbstractDaoService;
import com.sanlark.lib.core.datalayer.IDaoService;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.web.pojo.FileType;
import com.sanlark.vendormgmnt.pojo.common.AttachmentType;

public class AbstractAppDaoService<T, VO> extends AbstractDaoService implements IDaoService<T, VO>{

	@Override
	public void create(T obj) throws TransactionException {
		
	}

	@Override
	public void update(T obj) throws TransactionException {
		
	}

	@Override
	public void remove(T obj) throws TransactionException {
		
	}

	@Override
	public T get(long recordId) {
		return null;
	}

	@Override
	public List<T> list(String dbFilterQuery) {
		return null;
	}

	@Override
	public List<VO> filter(String dbFilterQuery) {
		return null;
	}

	@Override
	public VO view(long recordId) {
		return null;
	}
	
	protected void updateSkillBatch(String sql, final long primaryKeyId, 
			final int[] skillCol)throws TransactionException{
		int[] rtArr = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, primaryKeyId);
				ps.setInt(2, skillCol[i]);
			}

			@Override
			public int getBatchSize() {
				return skillCol.length;
			}
		});
		if(rtArr.length != skillCol.length){
			throw new TransactionException();
		}
	}
	
	protected List<MultipartFile> filterFiles(MultipartFile[] attachments){
		if(attachments == null || attachments.length == 0){
			return null;
		}
		List<MultipartFile> fileCol = new ArrayList<MultipartFile>();
		for(MultipartFile file : attachments){
			if(file.isEmpty()){
				continue;
			}
			fileCol.add(file);
		}
		return fileCol;
	}
	
	protected void updateFileBatch(final AttachmentType typeId, 
			final long refId, final List<MultipartFile> fileCol)throws TransactionException, SQLException{
		if(CommonUtil.hasElement(fileCol) == false){
			return;
		}
		
		int[] rtArr = jdbcTemplate.batchUpdate(Queries.CREATE_ATTACHMENT, 
				new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MultipartFile file = fileCol.get(i);
				String fileName = file.getOriginalFilename();
				FileType fileType  = FileType.get(fileName);
				
				ps.setInt(1, typeId.id);
				ps.setLong(2, refId);
				ps.setString(3, fileName);
				ps.setInt(4, fileType.id);
				try {
					ps.setBlob(5, file.getInputStream(), file.getSize());
				} catch (IOException e) {
					throw new SQLException(e.getMessage(), e);
				}
			}
			@Override
			public int getBatchSize() {
				return fileCol.size();
			}
		});
		if(rtArr.length != fileCol.size()){
			throw new TransactionException();
		}
	}
	
	protected void deleteFileBatch(final Long refId, 
			final List<Long> col) throws TransactionException{
		int[] rtArr = jdbcTemplate.batchUpdate(Queries.DELETE_ATTACHMENT, 
				new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, refId);
				ps.setLong(2, col.get(i));
			}
			@Override
			public int getBatchSize() {
				return col.size();
			}
		});
		if(col.size() != rtArr.length){
			throw new TransactionException();
		}
	}
}
