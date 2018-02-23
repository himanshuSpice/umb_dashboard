package com.spice.service.creation.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spice.service.creation.response.ApiCountResponse;

@Transactional
@Repository
public class DashBoardDao {
	
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
	
	public ApiCountResponse apiCount() throws Exception {
			String str = "call proc_get_module_detail()";
		return  jdbcTemplateObject.queryForObject(str,new Object[] {}, new ApiCountResponse());
	}
}
