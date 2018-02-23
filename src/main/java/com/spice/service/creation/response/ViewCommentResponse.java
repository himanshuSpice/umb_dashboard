package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;


@Data
public class ViewCommentResponse  implements RowMapper<ViewCommentResponse>{
	private String id;
	private String loginId;
	private String Username;
	private String serviceReqId;
	private String Title;
	private String comment;
	private String createdDate;
	private String lastModified;
	public ViewCommentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ViewCommentResponse response = new ViewCommentResponse();
		response.setId(rs.getString("id"));
		response.setLoginId(rs.getString("login_id"));
		response.setUsername(rs.getString("Username"));
		response.setServiceReqId(rs.getString("service_req_id"));
		response.setTitle(rs.getString("Title"));
		response.setComment(rs.getString("comment"));
		response.setCreatedDate(rs.getString("created_date"));
		response.setLastModified(rs.getString("last_modified"));
		return response;
	}
	

}
