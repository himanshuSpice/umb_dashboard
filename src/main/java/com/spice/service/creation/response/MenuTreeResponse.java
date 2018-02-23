package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class MenuTreeResponse implements RowMapper<MenuTreeResponse> {
	private String serviceId;
	private String serviceName;
	private String serviceCode;
	private String creatorId;
	private String parentServiceId;
	private String scope;
	private String dcs;
	private String circle;
	private String status;
	private String operator;
	private String subscriberType;
	private String description;
	private String createdOn;
	private String lastModified;
	private String versionMajor;
	private String versionMinor;
	private String version;
	
	public MenuTreeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuTreeResponse response = new MenuTreeResponse();
		response.setServiceId(rs.getString("service_id"));
		response.setServiceName(rs.getString("service_name"));
		response.setServiceCode(rs.getString("service_code"));
		response.setCreatorId(rs.getString("creator_id"));
		response.setParentServiceId(rs.getString("parent_service_id"));
		response.setScope(rs.getString("scope"));
		response.setDcs(rs.getString("dcs"));
		response.setCircle(rs.getString("circle"));
		response.setStatus(rs.getString("status"));
		response.setOperator(rs.getString("operator"));
		response.setSubscriberType(rs.getString("subscriber_type"));
		response.setDescription(rs.getString("description"));
		response.setCreatedOn(rs.getString("created_on"));
		response.setLastModified(rs.getString("last_modified"));
		response.setVersionMajor(rs.getString("version_major"));
		response.setVersionMinor(rs.getString("version_minor"));
		response.setVersion(rs.getString("version"));
		return response;
	}
}
