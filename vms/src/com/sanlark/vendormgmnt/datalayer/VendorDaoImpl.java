/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import static com.sanlark.vendormgmnt.datalayer.Queries.CREATE_VENDOR;
import static com.sanlark.vendormgmnt.datalayer.Queries.CREATE_VENDOR_PERSON_MAP;
import static com.sanlark.vendormgmnt.datalayer.Queries.CREATE_VENDOR_SKILL_MAP;
import static com.sanlark.vendormgmnt.datalayer.Queries.DELETE_VENDOR_PERSON_MAP;
import static com.sanlark.vendormgmnt.datalayer.Queries.DELETE_VENDOR_SKILL_MAP;
import static com.sanlark.vendormgmnt.datalayer.Queries.GET_ATTACHMENT_LIST;
import static com.sanlark.vendormgmnt.datalayer.Queries.GET_VENDOR_INFO;
import static com.sanlark.vendormgmnt.datalayer.Queries.GET_VENDOR_NAME_LIST;
import static com.sanlark.vendormgmnt.datalayer.Queries.UPDATE_VENDOR;
import static com.sanlark.vendormgmnt.datalayer.Queries.VIEW_VENDOR_SKILLS_INFO;
import static com.sanlark.vendormgmnt.datalayer.Queries.VIEW_VENDOR_SKILL_NAMES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanlark.lib.core.datalayer.DaoHelper;
import com.sanlark.lib.core.datalayer.SingleValRowMapper;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.web.util.WebUtil;
import com.sanlark.vendormgmnt.datalayer.mapper.AttachmentRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.ModelVendorRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.PersonInfoRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.VoVendorInfoRowMapper;
import com.sanlark.vendormgmnt.pojo.common.AttachmentType;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;
import com.sanlark.vendormgmnt.pojo.vendor.ModelVendorInfo;
import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;
import com.sanlark.vendormgmnt.vo.common.VoVendorInfo;

@Service
public class VendorDaoImpl extends AbstractAppDaoService<ModelVendorInfo, VoVendorInfo> implements IVendorDao{

	@Override
	@Transactional
	public void create(final ModelVendorInfo obj) throws TransactionException {
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {           
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(CREATE_VENDOR, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, obj.getCompanyName());
                ps.setString(2, obj.getDesc());
                ps.setString(3, obj.getLogo());
                ps.setLong(4, obj.getCreatedById());
                return ps;
            }
        }, holder);
		long vendorRecordId = holder.getKey().longValue();
		updateSkillBatch(CREATE_VENDOR_SKILL_MAP, vendorRecordId, obj.getSkills());
		
		if(obj.getPersonCol() != null && obj.getPersonCol().size() > 0){
			updatePersonBatch(vendorRecordId, obj.getPersonCol(), obj.getCreatedById());
		}
		
		try {
			List<MultipartFile> fileCol = filterFiles(obj.getAttachments());
			updateFileBatch(AttachmentType.VENDOR, vendorRecordId, fileCol);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TransactionException(e);
		}
	}
	
	@Override
	@Transactional
	public void update(final ModelVendorInfo obj) throws TransactionException {
		List<Long> deletedFiles = WebUtil.parseAccessId(obj.getDeletedFiles());
		
		jdbcTemplate.update(UPDATE_VENDOR, obj.getCompanyName(),
				obj.getDesc(), obj.getLogo(), obj.getUpdatedById(), obj.isActive(), obj.getRecordId());
		
		// Delete all skill of this vendor
		jdbcTemplate.update(DELETE_VENDOR_SKILL_MAP, obj.getRecordId());
		// Insert new skills
		updateSkillBatch(CREATE_VENDOR_SKILL_MAP, obj.getRecordId(), obj.getSkills());
		
		if(obj.getPersonCol() != null && obj.getPersonCol().size() > 0){
			// Delete all contacts first
			jdbcTemplate.update(DELETE_VENDOR_PERSON_MAP, obj.getRecordId());
			
			// insert new contacts
			updatePersonBatch(obj.getRecordId(), obj.getPersonCol(), obj.getUpdatedById());
		}
		
		

		// Delete attachments which are marked as deleted from the user
		if(deletedFiles != null){
			deleteFileBatch(obj.getRecordId(), deletedFiles);
		}
		// Upload new attachments
		try {
			List<MultipartFile> fileCol = filterFiles(obj.getAttachments());
			updateFileBatch(AttachmentType.VENDOR,  obj.getRecordId(), fileCol);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TransactionException(e);
		}
	}
	
	protected void updatePersonBatch(final Long vendorRecordId, 
			final List<PersonInfo> col, final Long userRefId) throws TransactionException{
		
		int[] rtArr = jdbcTemplate.batchUpdate(CREATE_VENDOR_PERSON_MAP, 
				new BatchPreparedStatementSetter() {
			boolean isDefaultAdded = false;
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				PersonInfo obj = col.get(i);
				ps.setString(1, obj.getName());
				ps.setLong(2, vendorRecordId);
				ps.setInt(3, obj.getDesignationId());
				ps.setString(4, obj.getProfileImage());
				ps.setString(5, obj.getEmail());
				ps.setString(6, obj.getContacts());
				if(isDefaultAdded == false){
					isDefaultAdded = true;
					ps.setBoolean(7, true);
				}else{
					ps.setBoolean(7, obj.isDefaultContact());
				}
				
				ps.setLong(8, userRefId);
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
	
	@Override
	public List<VoVendorInfo> filter(String dbFilterQuery) {
		return null;
	}

	@Override
	public List<VoVendorInfo> getNameList() {
		return jdbcTemplate.query(GET_VENDOR_NAME_LIST, new VoVendorInfoRowMapper(false));
	}

	@Override
	public VoVendorInfo view(long recordId) {
		VoVendorInfo vo = DaoHelper.getObject(jdbcTemplate, GET_VENDOR_INFO, 
				new VoVendorInfoRowMapper(true), recordId);
		if(vo == null){
			return null;
		}

		// Load Skills
		SingleValRowMapper<String> rowMapper = new SingleValRowMapper<String>();
		List<String> skillCol = jdbcTemplate.query(VIEW_VENDOR_SKILLS_INFO, rowMapper, recordId);
		vo.setSkillCol(skillCol);

		// Load Attachments
		List<VoAttachmentDtls> attachedFiles = jdbcTemplate.query(GET_ATTACHMENT_LIST, 
				new AttachmentRowMapper(false), AttachmentType.VENDOR.id, recordId);
		vo.setAttachedFiles(attachedFiles);
		
		// Load contacts
		String query = Queries.GET_VENDOR_CONTACT_PREFIX + " vendor_id=" + recordId;
		List<PersonInfo> personCol = jdbcTemplate.query(query, new PersonInfoRowMapper());
		vo.setPersonCol(personCol);
		
		return vo;
	}
	
	@Override
	public ModelVendorInfo get(long recordId) {
		ModelVendorInfo vo = DaoHelper.getObject(jdbcTemplate, GET_VENDOR_INFO, 
				new ModelVendorRowMapper(), recordId);
		if(vo == null){
			return null;
		}
		
		// Load Attachments
		List<VoAttachmentDtls> attachedFiles = jdbcTemplate.query(GET_ATTACHMENT_LIST, 
				new AttachmentRowMapper(false), AttachmentType.VENDOR.id, recordId);
		vo.setAttachedFiles(attachedFiles);
		WebUtil.buildAccessId(attachedFiles);
		
		// Load contacts
		String query = Queries.GET_VENDOR_CONTACT_PREFIX + " vendor_id=" + recordId;
		List<PersonInfo> personCol = jdbcTemplate.query(query, new PersonInfoRowMapper());
		vo.setPersonCol(personCol);
				
		return vo;
	}

	@Override
	public List<String> getSkills(Long recordId) {
		SingleValRowMapper<String> rowMapper = new SingleValRowMapper<String>();
		return jdbcTemplate.query(VIEW_VENDOR_SKILL_NAMES, rowMapper, recordId);
	}
}
