package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class DbResponse  implements RowMapper<DbResponse> {
	
	private String nodeId;
	private String parentId;
	private String responseText;
	private String editable;
	private String switchServiceId;
	private String copy;
	private String delete;
	private String change_status;
	private String status;
	
	public DbResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

		DbResponse menuTreeListDao = new DbResponse();
		menuTreeListDao.setNodeId(rs.getString("node_id"));
		menuTreeListDao.setParentId(rs.getString("parent_id"));
		menuTreeListDao.setResponseText(rs.getString("response_text"));
		menuTreeListDao.setEditable(rs.getString("editable"));
		menuTreeListDao.setSwitchServiceId(rs.getString("switch_service_id"));
		menuTreeListDao.setCopy(rs.getString("copy"));
		menuTreeListDao.setDelete(rs.getString("delete"));
		menuTreeListDao.setChange_status(rs.getString("change_status"));
		menuTreeListDao.setStatus(rs.getString("status"));
		return menuTreeListDao;
	}
}
