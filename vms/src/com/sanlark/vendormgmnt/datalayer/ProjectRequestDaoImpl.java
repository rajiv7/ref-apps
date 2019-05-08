/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 01-Jul-2015 
 */
package com.sanlark.vendormgmnt.datalayer;

import static com.sanlark.vendormgmnt.datalayer.Queries.CREATE_REQUEST;
import static com.sanlark.vendormgmnt.datalayer.Queries.CREATE_REQUEST_SKILL_MAP;
import static com.sanlark.vendormgmnt.datalayer.Queries.FILTER_REQUEST_LIST_PREFIX;
import static com.sanlark.vendormgmnt.datalayer.Queries.FILTER_REQUEST_LIST_SUFFIX;
import static com.sanlark.vendormgmnt.datalayer.Queries.GET_ATTACHMENT_LIST;
import static com.sanlark.vendormgmnt.datalayer.Queries.SELECT_REQUEST_LIST_PREFIX;
import static com.sanlark.vendormgmnt.datalayer.Queries.VIEW_REQUEST_QUERY;
import static com.sanlark.vendormgmnt.datalayer.Queries.VIEW_REQUEST_SKILL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanlark.lib.core.datalayer.DaoHelper;
import com.sanlark.lib.core.datalayer.SingleValRowMapper;
import com.sanlark.lib.core.datalayer.TransactionException;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.core.util.DateUtil;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.lib.web.util.AppConfigConst;
import com.sanlark.lib.web.util.WebUtil;
import com.sanlark.vendormgmnt.datalayer.mapper.AttachmentRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.ComboOptionRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.PersonInfoRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.ProjectRequestRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.VoProjectRequestRowMapper;
import com.sanlark.vendormgmnt.datalayer.mapper.VoVendorSkillCountRowMapper;
import com.sanlark.vendormgmnt.pojo.common.AttachmentType;
import com.sanlark.vendormgmnt.pojo.common.RequestStatusUpdateInfo;
import com.sanlark.vendormgmnt.pojo.common.VoAttachmentDtls;
import com.sanlark.vendormgmnt.pojo.dtls.ModelProjectRequest;
import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;
import com.sanlark.vendormgmnt.pojo.vendor.PersonInfo;
import com.sanlark.vendormgmnt.vo.common.VoProjectRequest;
import com.sanlark.vendormgmnt.vo.common.VoVendorSkillCount;

@Service
public class ProjectRequestDaoImpl extends AbstractAppDaoService<ModelProjectRequest, VoProjectRequest> implements IProjectRequestDao{

	@Override
	@Transactional
	public void create(final ModelProjectRequest obj)  throws TransactionException{
		List<MultipartFile> fileCol = filterFiles(obj.getAttachments());
		boolean isIncludeAtttachment = CommonUtil.hasElement(fileCol);
		obj.setIncludeAttachment(isIncludeAtttachment);
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {           
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(CREATE_REQUEST, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, obj.getTitle());
                ps.setString(2, obj.getDesc());
                ps.setInt(3, obj.getDurationId());
                ps.setInt(4, obj.getLocationId());
                ps.setInt(5, obj.getCategoryId());
                ps.setBoolean(6, obj.isIncludeAttachment());
                
                ps.setDate(7, DateUtil.formatUiDate(obj.getStartDate()));
                
                ps.setLong(8, obj.getCreatedById());
                return ps;
            }
        }, holder);
		long requestRcdId = holder.getKey().longValue();
		updateSkillBatch(CREATE_REQUEST_SKILL_MAP, requestRcdId, obj.getSkills());
		
		try {
			updateFileBatch(AttachmentType.REQUEST, requestRcdId, fileCol);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TransactionException(e);
		}
	}
	
	@Override
	@Transactional
	public void update(ModelProjectRequest obj) throws TransactionException {
		/*
		 * 1. Update details into request table
		 * 2. Delete all from skill batch table
		 * 3. Insert into skill batch table
		 * 4. delete from attachment table
		 * 5. Insert into attachment table  
		 */
		List<Long> deletedFiles = WebUtil.parseAccessId(obj.getDeletedFiles());
		
		List<MultipartFile> fileCol = filterFiles(obj.getAttachments());
		boolean isIncludeAtttachment = CommonUtil.hasElement(fileCol);
		
		jdbcTemplate.update(Queries.UPDATE_REQUEST, obj.getTitle(), obj.getDesc(), 
				obj.getDurationId(), obj.getLocationId(), obj.getCategoryId(), 
				RequestStatus.RE_SUBMITTED_TO_GTEC.id, isIncludeAtttachment,
				DateUtil.formatUiDate(obj.getStartDate()), obj.getUpdatedById(), obj.getRecordId());
		
		// Delete all skill of this request
		jdbcTemplate.update(Queries.DELETE_REQUEST_SKILL_MAP, obj.getRecordId());
		// Insert new required skills
		updateSkillBatch(CREATE_REQUEST_SKILL_MAP, obj.getRecordId(), obj.getSkills());
		
		// Delete attachments which are marked as deleted from the user
		if(deletedFiles != null){
			deleteFileBatch(obj.getRecordId(), deletedFiles);
		}
		// Upload new attachments
		try {
			updateFileBatch(AttachmentType.REQUEST,  obj.getRecordId(), fileCol);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TransactionException(e);
		}
	}

	@Override
	public ModelProjectRequest get(long recordId) {
		String query = SELECT_REQUEST_LIST_PREFIX + " where r.record_id=?";
		ModelProjectRequest vo = DaoHelper.getObject(jdbcTemplate, query, new ProjectRequestRowMapper(), recordId);
		if(vo == null){
			return null;
		}
		
		// Load Attachments
		List<VoAttachmentDtls> attachedFiles = jdbcTemplate.query(GET_ATTACHMENT_LIST, 
				new AttachmentRowMapper(false), AttachmentType.REQUEST.id, recordId);
		vo.setAttachedFiles(attachedFiles);
		WebUtil.buildAccessId(attachedFiles);
		
		return vo;
	}
	
	@Override
	public List<ModelProjectRequest> list(String dbFilterQuery) {
		if(CommonUtil.isEmpty(dbFilterQuery)){
			throw new RuntimeException("Please pass filter query to proceed");
		}
		String query = SELECT_REQUEST_LIST_PREFIX + dbFilterQuery;
		return jdbcTemplate.query(query, new ProjectRequestRowMapper());
	}

	@Override
	public List<VoProjectRequest> filter(String dbFilterQuery) {
		String query = FILTER_REQUEST_LIST_PREFIX;
		
		if(CommonUtil.isEmpty(dbFilterQuery) == false){
			query += dbFilterQuery + " ";
		}
		query += FILTER_REQUEST_LIST_SUFFIX;

		return jdbcTemplate.query(query, new VoProjectRequestRowMapper());
	}

	@Override
	public VoProjectRequest view(long recordId) {
		VoProjectRequest vo = DaoHelper.getObject(jdbcTemplate, VIEW_REQUEST_QUERY, 
				new VoProjectRequestRowMapper(), recordId);
		
		// Load Skills
		SingleValRowMapper<String> rowMapper = new SingleValRowMapper<String>();
		List<String> skillCol = jdbcTemplate.query(VIEW_REQUEST_SKILL, rowMapper, recordId);
		vo.setSkillCol(skillCol);
		
		// Load Attachments
		List<VoAttachmentDtls> attachedFiles = jdbcTemplate.query(GET_ATTACHMENT_LIST, 
				new AttachmentRowMapper(false), AttachmentType.REQUEST.id, recordId);
		vo.setAttachedFiles(attachedFiles);
		WebUtil.buildAccessId(attachedFiles);
		
		return vo;
	}

	@Override
	public int getStatus(Long recordId) {
		SingleValRowMapper<Integer> rowMapper = new SingleValRowMapper<Integer>();
		Integer currentStatus = DaoHelper.getObject(jdbcTemplate, Queries.GET_REQUEST_STATUS, 
				rowMapper, recordId);
		return currentStatus;
	}

	@Override
	@Transactional
	public void updateStatus(RequestStatusUpdateInfo updateInfo) throws TransactionException {
		
		String txnDate = CommonUtil.trim(updateInfo.getTxnDate());
		txnDate = DateUtil.getDbDate(txnDate);
		
		RequestStatus newRequestStatus = updateInfo.getNewStatus();
		StringBuilder query = new StringBuilder("UPDATE dtls_request set request_status=" + newRequestStatus.id);
		if(newRequestStatus == RequestStatus.ACCEPTED_BY_GTEC){
			query.append(", close_date='" + DateUtil.getDbDate(updateInfo.getTxnDate()) + "'");
			query.append(", final_vendor_id=" + AppConfigConst.GTEC_VENDOR_ID);
		}else if(newRequestStatus == RequestStatus.VENDOR_SELECTED){
			query.append(", close_date='" + DateUtil.getDbDate(updateInfo.getTxnDate()) + "'");
			query.append(", final_vendor_id=" + updateInfo.getVendorId());
			query.append(", po_no='" + updateInfo.getPo() + "'");
		}else if(newRequestStatus == RequestStatus.WORK_COMPLETED){
			query.append(", delivery_date='" + DateUtil.getDbDate(updateInfo.getTxnDate()) + "'");
		}
		query.append(", updated_by_id=" + updateInfo.getAccessTicket().getRecordId());
		query.append(" where record_id=" + updateInfo.getRecordId());
		
		int rt = jdbcTemplate.update(query.toString());
		if(rt != 1){
			throw new TransactionException("Server Error");
		}
	}

	@Override
	public List<VoVendorSkillCount> getMatchedVendors(Long recordId) {
		return jdbcTemplate.query(Queries.GET_MATCHED_VENDORS, 
				new VoVendorSkillCountRowMapper(), recordId);
	}

	@Override
	public List<PersonInfo> getVendorContact(List<Long> vendorCol){
		if(CommonUtil.hasElement(vendorCol) == false){
			return null;
		}
		StringBuilder buf = new StringBuilder();
		
		for(Long recdId : vendorCol){
			buf.append(recdId + ",");
		}
		String query = Queries.GET_VENDOR_CONTACT_PREFIX + " is_default=1 and vendor_id in (" + buf.deleteCharAt(buf.length()-1) + ")";
		return jdbcTemplate.query(query, new PersonInfoRowMapper());
	}

	@Override
	@Transactional
	public void sendProposal(final Long requestId, final List<KeyValPair<Long, String>> contactCol) throws TransactionException {
		if(CommonUtil.hasElement(contactCol) == false){
			return;
		}
		
		int[] rtArr = jdbcTemplate.batchUpdate(Queries.CREATE_VENDOR_PROPROSAL, 
				new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				KeyValPair<Long, String> contact = contactCol.get(i);
				ps.setLong(1, requestId);
				ps.setLong(2, contact.getKey());
				ps.setString(3, contact.getValue());
			}
			@Override
			public int getBatchSize() {
				return contactCol.size();
			}
		});
		if(contactCol.size() != rtArr.length){
			throw new TransactionException();
		}
		
		String query = "UPDATE dtls_request set request_status=" + RequestStatus.SUBMITTED_TO_VENDORS.id + 
				" where record_id=" + requestId;
		
		int rt = jdbcTemplate.update(query);
		if(rt != 1){
			throw new TransactionException("Server Error");
		}
	}

	@Override
	public List<Long> getSentProposalByRequestId(Long recordId) {
		SingleColumnRowMapper<Long> rowMapper = new SingleColumnRowMapper<Long>();
		return jdbcTemplate.query(Queries.GET_SENT_VENDOR_PROPROSAL, rowMapper, recordId);
	}

	@Override
	public List<ComboOption> getVendorFromSentProposal(Long requestId) {
		return jdbcTemplate.query(Queries.GET_VENDOR_FROM_SENT_PROPROSAL, 
				new ComboOptionRowMapper(), requestId);
	}

	@Override
	public String getSelectedVendor(Long recordId) {
		String query = "select v.company_name from master_vendor v, dtls_request r where v.record_id = r.final_vendor_id and r.record_id=?";
		SingleColumnRowMapper<String> rowMapper = new SingleColumnRowMapper<String>();
		return DaoHelper.getObject(jdbcTemplate, query, rowMapper, recordId);
	}
}
