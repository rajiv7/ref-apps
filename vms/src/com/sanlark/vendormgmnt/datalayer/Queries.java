/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.datalayer;

public interface Queries {
	String LOGIN = "select ld.FullName, ld.Email, roles.* from"
			+ " core_employee_login_details_v ld, core_espace_system_roles_v roles, core_espace_user_sysroles_mapping_v map"
			+ " where map.RoleId = roles.RoleId and map.LoginName = ld.EmployeeLoginName"
			+ " and roles.Active = 1 and ld.Active = 1 and ld.userid = ?";
	
	/*
	 * Request related Queries
	 */
	String CREATE_REQUEST = "INSERT into dtls_request (request_title,description,duration_id,location_id,category_id,"
			+ "is_include_attachment,start_date,created_by_id) VALUES(?,?,?,?,?,?,?,?)";
	
	String UPDATE_REQUEST = "UPDATE dtls_request set request_title=?,description=?,"
			+ "duration_id=?,location_id=?,category_id=?,request_status=?,is_include_attachment=?,start_date=?,updated_by_id=? where record_id=?";
	
	String CREATE_REQUEST_SKILL_MAP = "INSERT into batch_request_skill_map (request_id, skill_id) VALUES(?,?)";
	String DELETE_REQUEST_SKILL_MAP = "DELETE from batch_request_skill_map where request_id=?"; 
	
	String SELECT_REQUEST_LIST_PREFIX = "select * from dtls_request r";
	
	String FILTER_REQUEST_LIST_PREFIX = "select r.record_id, r.request_title, r.description, r.duration_id, r.start_date, r.request_status, r.created_date,"
			+ "l.title as location_name, c.title as category_name, r.is_include_attachment, r.created_by_id from dtls_request r, master_service_location l, master_service_category c "
			+ "where r.location_id = l.record_id and r.category_id = c.record_id";
	String FILTER_REQUEST_LIST_SUFFIX = " order by r.created_date DESC";
	
	String VIEW_REQUEST_QUERY = "select r.record_id, r.request_title, r.description, r.duration_id, r.start_date, r.request_status, r.created_date,"
			+ "l.title as location_name, c.title as category_name, r.is_include_attachment, r.created_by_id from dtls_request r, master_service_location l, master_service_category c "
			+ "where r.location_id = l.record_id and r.category_id = c.record_id and r.record_id=?";
	String VIEW_REQUEST_SKILL = "select m.title from master_service_skill m, batch_request_skill_map b where b.skill_id=m.record_id and b.request_id=?";
	
	String VIEW_VENDOR_SKILLS_INFO  = "SELECT title FROM master_service_skill where record_id in ( select skill_id FROM master_vendor_skill_map where  vendor_id = ? )";
	String VIEW_ATTACHMENTS  = "SELECT title FROM master_service_skill where record_id in ( select skill_id FROM master_vendor_skill_map where  vendor_id = ? )";
	
	String VIEW_VENDOR_SKILL_NAMES  = "select s.title from master_vendor_skill_map vs, master_service_skill s where vs.skill_id = s.record_id and vs.vendor_id=?";

	String GET_REQUEST_STATUS = "SELECT request_status from dtls_request where record_id=?";
	
	String CREATE_VENDOR_PROPROSAL = "INSERT into batch_vendor_proposal_map (request_id,vendor_id,sent_email) VALUES(?,?,?)";
	String GET_SENT_VENDOR_PROPROSAL = "SELECT vendor_id from batch_vendor_proposal_map where request_id=?";
	String GET_VENDOR_FROM_SENT_PROPROSAL = "select v.record_id, v.company_name, v.status_id from master_vendor v, " +
			"batch_vendor_proposal_map m where v.record_id = m.vendor_id and m.request_id=?";
	
	/*
	 * Vendor Related Queries
	 */
	String CREATE_VENDOR = "INSERT into master_vendor (company_name,description,company_logo,created_by_id) VALUES(?,?,?,?)";
	String UPDATE_VENDOR = "UPDATE master_vendor set company_name=?,description=?,company_logo=?,updated_by_id=?, status_id=? where record_id=?";
	
	String CREATE_VENDOR_SKILL_MAP = "INSERT into master_vendor_skill_map (vendor_id, skill_id) VALUES(?, ?)";
	String DELETE_VENDOR_SKILL_MAP = "DELETE from master_vendor_skill_map where vendor_id=?";
	
	String CREATE_VENDOR_PERSON_MAP = "INSERT into master_vendor_contact_map "
			+ "(person_name, vendor_id, designation_id, profile_image, email, contact_no, is_default, created_by_id) VALUES(?,?,?,?,?,?,?,?)";
	String DELETE_VENDOR_PERSON_MAP = "DELETE from master_vendor_contact_map where vendor_id=?";
	
	String FILTER_VENDOR_LIST_SUFFIX = " order by v.company_name";
	
	String GET_VENDOR_NAME_LIST = "select v.record_id, v.company_name, v.company_logo, v.status_id from master_vendor v" + FILTER_VENDOR_LIST_SUFFIX;
	String GET_VENDOR_INFO = "select v.record_id, v.company_name, v.company_logo, v.status_id, v.description from master_vendor v where v.record_id=?";
	
	String CREATE_ATTACHMENT = "INSERT INTO batch_attachment (type_id, ref_id, file_name, file_type, file_data) VALUES (?, ?, ?, ?, ?)";
	String GET_ATTACHMENT__PREFIX_PART = "select a.record_id, a.file_name, a.file_type, OCTET_LENGTH(file_data) as file_length";
	String GET_ATTACHMENT_LIST = GET_ATTACHMENT__PREFIX_PART + " from batch_attachment a where a.type_id=? and a.ref_id=?";
	String GET_ATTACHMENT_DATA = GET_ATTACHMENT__PREFIX_PART + ", a.file_data from batch_attachment a where a.record_id=?";
	String DELETE_ATTACHMENT = "DELETE from batch_attachment where ref_id=? and record_id=?";
	
	/*
	 * Report like queries
	 */
	String GET_MATCHED_VENDORS = "select v.record_id, v.company_name, count(rs.record_id) as matched_count" + 
			" from master_vendor v, batch_request_skill_map rs, master_vendor_skill_map vs" +
			" where vs.vendor_id = v.record_id and vs.skill_id = rs.skill_id" +
			" and v.status_id=1 and rs.request_id=?" + 
			" group by v.record_id, v.company_name  order by matched_count DESC, company_name ASC";
	
	String GET_VENDOR_CONTACT_PREFIX = "select record_id, vendor_id, person_name, designation_id, email, contact_no, is_default from master_vendor_contact_map where";
}
