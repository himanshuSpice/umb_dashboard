package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;


@Data
public class ViewLogsResponse implements RowMapper<ViewLogsResponse> {
	private String id;
	private String serviceId;
	private String serviceName;
	private String deploymentType;
	private String Username;
	private String logDate;

	public ViewLogsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ViewLogsResponse response = new ViewLogsResponse();
		response.setId(rs.getString("id"));
		response.setServiceId(rs.getString("service_id"));
		response.setServiceName(rs.getString("service_name"));
		response.setDeploymentType(rs.getString("deployment_type"));
		response.setUsername(rs.getString("Username"));
		response.setLogDate(rs.getString("log_date"));
		return response;
	}
}
