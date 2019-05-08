package com.sanlark.vendormgmnt.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.transaction.Transactional;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.sanlark.lib.core.datalayer.AbstractDaoService;
import com.sanlark.lib.core.datalayer.DaoHelper;
import com.sanlark.lib.web.pojo.AccessTicket;
import com.sanlark.lib.web.pojo.UserRole;
import com.sanlark.vendormgmnt.datalayer.mapper.AccessTicketRowMapper;

public class UserDaoImpl extends AbstractDaoService implements IUserDao {

	@Override
	@Transactional
	public void update(final AccessTicket accessTicket) {
		try{
			String query = "SELECT record_id,profile_name,user_role_id,status_id from master_app_user_info where login_id=?";
			AccessTicket ticket = DaoHelper.getObject(jdbcTemplate, query, 
					new AccessTicketRowMapper(), accessTicket.getLoginId());
			if(ticket == null){
				if(accessTicket.getUserRole() == null){
					// Bypass setting from service for hard coded values
					accessTicket.setUserRole(UserRole.REQUESTER);
				}
				final String createQuery = "INSERT into master_app_user_info (login_id,profile_name,user_role_id) values(?,?,?)";
				KeyHolder holder = new GeneratedKeyHolder();
				
				jdbcTemplate.update(new PreparedStatementCreator() {           
		            @Override
		            public PreparedStatement createPreparedStatement(Connection connection)
		                    throws SQLException {
		                PreparedStatement ps = connection.prepareStatement(createQuery, 
		                		Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1, accessTicket.getLoginId());
		                ps.setString(2, accessTicket.getName());
		                ps.setInt(3, accessTicket.getUserRole().id);
		                return ps;
		            }
		        }, holder);
				long recordId = holder.getKey().longValue();
				accessTicket.setRecordId(recordId);
				accessTicket.setActive(true);
			}else{
				accessTicket.setRecordId(ticket.getRecordId());
				accessTicket.setActive(ticket.isActive());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
