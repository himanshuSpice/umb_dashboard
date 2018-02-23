package com.spice.service.creation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spice.service.creation.response.ApiListResponse;


@Transactional
@Repository
public class ApiDao {
	
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
	
	public List<ApiListResponse> getApiList() throws Exception {
		String str = "select app_id, app_name, app_mtype, app_description, mandatory_params, optional_params, app_response, status, created_on, last_modified from tbl_application_master";
		return  jdbcTemplateObject.query(str,new Object[] {}, new ApiListResponse());
	}
	

}
