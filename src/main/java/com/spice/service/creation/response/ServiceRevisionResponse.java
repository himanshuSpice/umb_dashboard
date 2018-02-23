package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ServiceRevisionResponse implements RowMapper<ServiceRevisionResponse> {
	private String revisionId;
	private String serviceId;
	private String serviceName;
	private String serviceCode;
	private String modifiedBy;
	private String newVersionMajor;
	private String newVersionMinor;
	private String remarks;
	private String createdOn;
	private String version;
	public ServiceRevisionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ServiceRevisionResponse response = new ServiceRevisionResponse();
		response.setRevisionId(rs.getString("revision_id"));
		response.setServiceId(rs.getString("service_id"));
		response.setServiceName(rs.getString("service_name"));
		response.setServiceCode(rs.getString("service_code"));
		response.setModifiedBy(rs.getString("modified_by"));
		response.setNewVersionMajor(rs.getString("new_version_major"));
		response.setNewVersionMinor(rs.getString("new_version_minor"));
		response.setRemarks(rs.getString("remarks"));
		response.setCreatedOn(rs.getString("created_on"));
		response.setVersion(rs.getString("version"));
		return response;
	}
	

}
