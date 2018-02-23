package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ViewEditServiceResponse implements RowMapper<ViewEditServiceResponse> {
	private String editId;
	private String serviceId;
	private String serviceName;
	private String serviceCode;
	private String creatorId;
	private String parentServiceId;
	private String status;
	private String modifiedBy;
	private String currentVersionMajor;
	private String currentVersionMinor;
	private String newVersionMajor;
	private String newVersionMinor;
	private String versionOld;
	private String versionNew;
	private String remarks;
	private String createdOn;
	private String lastModified;
	public ViewEditServiceResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ViewEditServiceResponse response = new ViewEditServiceResponse();
		response.setEditId(rs.getString("edit_id"));
		response.setServiceId(rs.getString("service_id"));
		response.setServiceName(rs.getString("service_name"));
		response.setServiceCode(rs.getString("service_code"));
		response.setCreatorId(rs.getString("creator_id"));
		response.setParentServiceId(rs.getString("parent_service_id"));
		response.setStatus(rs.getString("status"));
		response.setModifiedBy(rs.getString("modified_by"));
		response.setCurrentVersionMajor(rs.getString("current_version_major"));
		response.setCurrentVersionMinor(rs.getString("current_version_minor"));
		response.setNewVersionMajor(rs.getString("new_version_major"));
		response.setNewVersionMinor(rs.getString("new_version_minor"));
		response.setRemarks(rs.getString("remarks"));
		response.setCreatedOn(rs.getString("created_on"));
		response.setLastModified(rs.getString("last_modified"));
		response.setVersionOld(rs.getString("version_old"));
		response.setVersionNew(rs.getString("version_new"));
		return response;
	}
	

}
