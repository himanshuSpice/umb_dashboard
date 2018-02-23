package com.spice.service.creation.response;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ViewMsisdnResponse implements RowMapper<ViewMsisdnResponse> {
	private String id;
	private String msisdn;
	private String serviceCode;
	private String serviceId;
	private String creatorName;
	private String creationOn;
	public ViewMsisdnResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ViewMsisdnResponse response = new  ViewMsisdnResponse();
		response.setId(rs.getString("id"));
		response.setMsisdn(rs.getString("msisdn"));
		response.setServiceCode(rs.getString("service_code"));
		response.setServiceId(rs.getString("service_id"));
		response.setCreatorName(rs.getString("creator_name"));
		response.setCreationOn(rs.getString("creation_on"));
		return response;
	}
}
