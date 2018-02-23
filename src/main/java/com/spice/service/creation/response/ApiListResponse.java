package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ApiListResponse implements RowMapper<ApiListResponse> {
	private String appId;
	private String appName;
	private String appMtype;
	private String appDescription;
	private String mandatoryParams	;
	private String optionalParams;
	private String appResponse;
	private String status;
	private String createdOn;
	private String lastModified;
	public ApiListResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApiListResponse response = new ApiListResponse();
		response.setAppId(rs.getString("app_id"));
		response.setAppName(rs.getString("app_name"));
		response.setAppMtype(rs.getString("app_mtype"));
		response.setAppDescription(rs.getString("app_description"));
		response.setMandatoryParams(rs.getString("mandatory_params"));
		response.setOptionalParams(rs.getString("optional_params"));
		response.setAppResponse(rs.getString("app_response"));;
		response.setStatus(rs.getString("status"));
		response.setCreatedOn(rs.getString("created_on"));
		response.setLastModified(rs.getString("last_modified"));
		return response;
	}	
}
