package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class ServiceNameDao implements RowMapper<ServiceNameDao>{
	
	private String serviceName;

	public ServiceNameDao mapRow(ResultSet rs, int rowNum) throws SQLException {
		ServiceNameDao serviceNameDao = new ServiceNameDao();
		serviceNameDao.setServiceName(rs.getString("service_CODE"));
		return serviceNameDao;
	}

}
