package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class VariableListResponse implements RowMapper<VariableListResponse> {
	private String variableId;
	private String variableName;
	private String variableType;
	private String variableRegex;
	private String status;
	private String createdOn;
	private String lastModified;
	public VariableListResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		VariableListResponse response = new VariableListResponse();
		response.setVariableId(rs.getString("variable_id"));
		response.setVariableName(rs.getString("variable_name"));
		response.setVariableType(rs.getString("variable_type"));
		response.setVariableRegex(rs.getString("variable_regex"));
		response.setStatus(rs.getString("status"));
		response.setCreatedOn(rs.getString("created_on"));
		response.setLastModified(rs.getString("last_modified"));
		return response;
	}
	

}
