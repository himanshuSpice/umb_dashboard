package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ApiCountResponse implements RowMapper<ApiCountResponse>{
	
	private String totalActiveUser;
	private String totalActiveService;
	private String totalApi;
	
	public ApiCountResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApiCountResponse countResponse = new ApiCountResponse();
		countResponse.setTotalActiveService(rs.getString("total_active_user"));
		countResponse.setTotalActiveUser(rs.getString("total_active_service"));
		countResponse.setTotalApi(rs.getString("total_api"));
		return countResponse;
	}

}
