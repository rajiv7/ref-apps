CREATE TABLE IF NOT EXISTS master_app_user_info (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  login_id varchar(256) NOT NULL,
  profile_name varchar(64) DEFAULT NULL,
  user_role_id int(1) NOT NULL,
  status_id bit(1) NOT NULL DEFAULT b'1',
  is_logged_in bit(1) NOT NULL DEFAULT b'0',
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS master_designation (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(32) NOT NULL,
  description varchar(256) DEFAULT NULL,
  status_id bit(1) NOT NULL DEFAULT b'1',
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS master_service_category (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(32) NOT NULL,
  description varchar(256) DEFAULT NULL,
  status_id bit(1) NOT NULL DEFAULT b'1',
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS master_service_location (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(32) NOT NULL,
  description varchar(256) DEFAULT NULL,
  status_id bit(1) NOT NULL DEFAULT b'1',
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS master_service_skill (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(32) NOT NULL,
  description varchar(256) DEFAULT NULL,
  status_id bit(1) NOT NULL DEFAULT b'1',
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS master_vendor (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  company_name varchar(128) NOT NULL,
  description text,
  status_id bit(1) NOT NULL DEFAULT b'1',
  company_logo varchar(256) DEFAULT NULL,
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS master_vendor_skill_map (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  vendor_id int(10) unsigned NOT NULL,
  skill_id int(10) unsigned NOT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS master_vendor_contact_map (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  person_name varchar(64) NOT NULL,
  vendor_id int(10) unsigned NOT NULL,
  designation_id int(10) unsigned DEFAULT NULL,
  is_default bit(1) NOT NULL DEFAULT b'0',
  profile_image varchar(256) DEFAULT NULL,
  email varchar(256) DEFAULT NULL,
  contact_no varchar(64) DEFAULT NULL,
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS batch_attachment (
  record_id bigint(10) NOT NULL AUTO_INCREMENT,
  type_id int(2) NOT NULL,
  ref_id bigint(10) NOT NULL,
  file_name varchar(256) NOT NULL,
  file_type tinyint(1) NOT NULL,
  file_data longblob NOT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS dtls_request (
  record_id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  request_title varchar(256) NOT NULL,
  description text,
  duration_id int(2) NOT NULL,
  location_id int(10) NOT NULL,
  category_id int(10) NOT NULL,
  is_include_attachment bit(1) NOT NULL DEFAULT b'0',
  start_date date DEFAULT NULL,
  close_date date DEFAULT NULL,
  delivery_date date DEFAULT NULL,
  request_status tinyint(2) NOT NULL DEFAULT '1',
  final_vendor_id int(10) unsigned DEFAULT NULL,
  po_no varchar(256) DEFAULT NULL,
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_id int(10) unsigned DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  updated_by_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS batch_request_skill_map (
  record_id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  request_id bigint(20) unsigned NOT NULL,
  skill_id int(10) unsigned NOT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS batch_vendor_proposal_map (
  record_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  request_id bigint(20) unsigned NOT NULL,
  vendor_id int(10) unsigned NOT NULL,
  sent_email varchar(256) NOT NULL,
  PRIMARY KEY (record_id)
) ENGINE=InnoDB  DEFAULT CHARSET=ascii AUTO_INCREMENT=1;