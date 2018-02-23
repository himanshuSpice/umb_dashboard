package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class UserDetailsResponse  implements RowMapper<UserDetailsResponse>{
	private String userId;
	private String createdBy;
	private String userName;
	private String msisdn;
	private String userIp;
	private String status;
	private String email;
	private String createdDate 	;
	private String modifiedDate;
	private String userMngtPermission;
	private String serviceMngtPermission;
	public UserDetailsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDetailsResponse response = new  UserDetailsResponse();
		response.setCreatedBy(rs.getString("created_by"));
		response.setUserName(rs.getString("username"));
		response.setMsisdn(rs.getString("msisdn"));
		response.setUserIp(rs.getString("user_ip"));
		response.setStatus(rs.getString("status"));
		response.setEmail(rs.getString("email"));
		response.setCreatedDate(rs.getString("created_date"));
		response.setModifiedDate(rs.getString("modified_date"));
		response.setUserMngtPermission(rs.getString("user_mngt_permission"));
		response.setServiceMngtPermission(rs.getString("service_mngt_permission"));
		response.setUserId(rs.getString("user_master_id"));
		return response;
	}

}
