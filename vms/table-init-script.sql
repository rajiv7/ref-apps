INSERT INTO `master_app_user_info` (`login_id`, `profile_name`, `user_role_id`, `status_id`, `is_logged_in`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('admin', 'Administrator', 1, b'1', b'0', '2015-07-18 17:43:13', NULL, NULL, NULL);
INSERT INTO `master_app_user_info` (`login_id`, `profile_name`, `user_role_id`, `status_id`, `is_logged_in`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('kumar28', 'Rajiv Kumar', 4, b'1', b'0', '2015-07-18 19:39:44', NULL, NULL, NULL);
INSERT INTO `master_app_user_info` (`login_id`, `profile_name`, `user_role_id`, `status_id`, `is_logged_in`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('gtec', 'Kumar', 2, b'1', b'0', '2015-07-18 22:04:26', NULL, NULL, NULL);
INSERT INTO `master_app_user_info` (`login_id`, `profile_name`, `user_role_id`, `status_id`, `is_logged_in`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('proc', 'Rajiv', 3, b'1', b'0', '2015-07-18 19:55:26', NULL, '2015-07-18 19:56:47', NULL);

--
-- Dumping data for table `master_designation`
--

INSERT INTO `master_designation` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Account Manager', NULL, b'1', '2015-07-19 01:44:55', 103, NULL, NULL);
INSERT INTO `master_designation` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Engineering Manager', NULL, b'1', '2015-07-19 01:44:55', 103, NULL, NULL);
INSERT INTO `master_designation` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Technical Lead', NULL, b'1', '2015-07-19 01:44:55', 103, NULL, NULL);
INSERT INTO `master_designation` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Automation Lead', NULL, b'1', '2015-07-19 01:44:55', 103, NULL, NULL);
INSERT INTO `master_designation` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Sales Manager', NULL, b'1', '2015-07-19 07:31:23', 103, NULL, NULL);

--
-- Dumping data for table `master_service_category`
--

INSERT INTO `master_service_category` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('NBC', NULL, b'1', '2015-07-18 22:01:08', 103, NULL, NULL);
INSERT INTO `master_service_category` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('JennAir', NULL, b'1', '2015-07-18 22:01:08', 103, NULL, NULL);
INSERT INTO `master_service_category` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Commercial Laundry', NULL, b'1', '2015-07-18 22:01:08', 103, NULL, NULL);
INSERT INTO `master_service_category` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Water Filtration', NULL, b'1', '2015-07-18 22:01:08', 103, NULL, NULL);
INSERT INTO `master_service_category` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Air Filtration', NULL, b'1', '2015-07-18 22:01:08', 103, NULL, NULL);
INSERT INTO `master_service_category` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Gladiator', NULL, b'1', '2015-07-18 22:01:08', 103, NULL, NULL);

--
-- Dumping data for table `master_service_location`
--

INSERT INTO `master_service_location` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('SJTC', NULL, b'1', '2015-07-18 22:00:23', 103, NULL, NULL);
INSERT INTO `master_service_location` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('BHTC', NULL, b'1', '2015-07-18 22:00:23', 103, NULL, NULL);
INSERT INTO `master_service_location` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Greenville OH', NULL, b'1', '2015-07-18 22:00:23', 103, NULL, NULL);
INSERT INTO `master_service_location` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Garage', NULL, b'1', '2015-07-18 22:00:23', 103, NULL, NULL);
INSERT INTO `master_service_location` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Amana IA', NULL, b'1', '2015-07-18 22:00:23', 103, NULL, NULL);
INSERT INTO `master_service_location` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('GTEC', NULL, b'1', '2015-07-18 22:00:23', 103, NULL, NULL);

--
-- Dumping data for table `master_service_skill`
--

INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Structures', NULL, b'1', '2015-07-18 20:00:28', NULL, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Prototyping', NULL, b'1', '2015-07-18 21:50:19', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Electromechanical', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Mechanical', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('CAD', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Hardware', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Software/Application', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Aesthetics', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);
INSERT INTO `master_service_skill` (`title`, `description`, `status_id`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Packaging', NULL, b'1', '2015-07-18 21:52:58', 103, NULL, NULL);

--
-- Dumping data for table `master_vendor`
--

INSERT INTO `master_vendor` (`company_name`, `description`, `status_id`, `company_logo`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('GTEC', 'GTEC Company Overview', b'1', NULL, '2015-07-19 01:03:20', NULL, '2015-07-19 01:33:32', 103);
INSERT INTO `master_vendor` (`company_name`, `description`, `status_id`, `company_logo`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('L & T Infotech', '<p>L&T Technology Services (L&T TS), a Strategic Business Unit of L&T, leverages the same value system as of its parent organization in the field of Engineering Services. It provides solutions in Mechanical Engineering, Plant Engineering, Civil & Architectural Services, Embedded systems & Software Services. L&T TS serves several Fortune 500 companies and employs more than 8000 engineers including a large number of domain experts from various industry segments like Industrial Products, Consumer Goods, Automotive, Aerospace, Trucks, Off Highway Vehicles, Medical Devices & CPG, and Oil & Gas to name a few.</p><p>L&T TS is an organization which has the unique ability to design keeping Build in perspective. L&T TS can provide different offerings to clients to become a one stop solution partner. As a forward looking financially strong organization it is willing and able to invest in key relationships. Its brand name attracts the best of the talents, which assures professionalism and excellent workmanship. It firmly believes that it has the right competence, capability, and most of all commitment to create a strong value driven partnership with Sanlark Corporation.</p><p>L&T TS wide experience and capability to manage end to end delivery of projects for the world''s major business houses has helped L&T TS to be at top of the learning curve that in turn helps in serving the customer in an optimum way to meet their business requirements. </p><p>L&T TS operates from dedicated on-shore and offshore engineering centers with onsite teams to cater to engineering requirements of global clients, many of them Fortune 500 Companies.</p><p>L&T TS''s credentials include ISO 9001: 2008 for Quality Management System, ISO 27001 Certification for IT Security Management System. L&T TS is a leading global engineering services provider and has worked towards creating a work environment conducive for efficiency and innovation. </p> ', b'1', NULL, '2015-07-19 01:08:46', 103, '2015-07-19 01:34:46', 103);
INSERT INTO `master_vendor` (`company_name`, `description`, `status_id`, `company_logo`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Ora Infotech', 'USA based IT Company', b'1', NULL, '2015-07-19 08:00:57', 103, NULL, NULL);
INSERT INTO `master_vendor` (`company_name`, `description`, `status_id`, `company_logo`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Hudson Group', 'Tst', b'1', NULL, '2015-07-19 08:07:20', 103, NULL, NULL);

--
-- Dumping data for table `master_vendor_contact_map`
--

INSERT INTO `master_vendor_contact_map` (`person_name`, `vendor_id`, `designation_id`, `is_default`, `profile_image`, `email`, `contact_no`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Rajiv', 100, 104, b'1', NULL, 'rajiv@sanlark.com', '', '2015-07-19 07:31:44', 103, '2015-07-19 07:54:59', NULL);
INSERT INTO `master_vendor_contact_map` (`person_name`, `vendor_id`, `designation_id`, `is_default`, `profile_image`, `email`, `contact_no`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Bryan', 101, 104, b'1', NULL, 'bryan@lnt.com', '', '2015-07-19 07:32:10', 103, '2015-07-19 07:55:27', NULL);
INSERT INTO `master_vendor_contact_map` (`person_name`, `vendor_id`, `designation_id`, `is_default`, `profile_image`, `email`, `contact_no`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Ora Admin', 102, 104, b'1', NULL, 'sales@ora.com', '', '2015-07-19 08:00:57', 103, '2015-07-19 08:07:34', NULL);
INSERT INTO `master_vendor_contact_map` (`person_name`, `vendor_id`, `designation_id`, `is_default`, `profile_image`, `email`, `contact_no`, `created_date`, `created_by_id`, `updated_date`, `updated_by_id`) VALUES('Hudson Smith', 103, 104, b'1', NULL, 'smith@hudson.com', '', '2015-07-19 08:07:20', 103, NULL, NULL);
