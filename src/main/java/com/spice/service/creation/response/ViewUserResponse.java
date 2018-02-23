package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ViewUserResponse implements RowMapper<ViewUserResponse> {
	private String userId;
	private String userType;
	private String userName;
	private String msisdn;
	private String email;
	private String status;
	private String umPermission;
	private String scPermission;
	private String createdBy;
	private String createdDate;
	private String modifiedDate;
	public ViewUserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ViewUserResponse response = new ViewUserResponse();
		response.setUserId(rs.getString("user_id"));
		response.setUserType(rs.getString("user_type"));
		response.setUserName(rs.getString("username"));
		response.setMsisdn(rs.getString("msisdn"));
		response.setEmail(rs.getString("email"));
		response.setStatus(rs.getString("status"));
		response.setUmPermission(rs.getString("um_permission"));
		response.setScPermission(rs.getString("sc_permission"));
		response.setCreatedBy(rs.getString("created_by"));
		response.setCreatedDate(rs.getString("created_date"));
		response.setModifiedDate(rs.getString("modified_date"));
		return response;
	}

}
