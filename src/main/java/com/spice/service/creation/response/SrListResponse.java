package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;


@Data
public class SrListResponse implements RowMapper<SrListResponse>{
	private String id;
	private String name;
	private String desc;
	private String title;
	private String assignee;
	private String targetDate;
	private String status;
	private String createdDate;
	private String createdBy;
	private String assigneeId;
	private String asigneeName;
	private String assigneeUsertype;
	private String modifiedBy;
	private String modifiedDate;
	public SrListResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		SrListResponse srListResponse = new SrListResponse();
				srListResponse.setId(rs.getString("id"));
				srListResponse.setName(rs.getString("name"));
				srListResponse.setDesc(rs.getString("desc"));
				srListResponse.setTitle(rs.getString("title"));
				srListResponse.setAssignee(rs.getString("assignee"));
				srListResponse.setTargetDate(rs.getString("target_date"));
				srListResponse.setStatus(rs.getString("status"));
				srListResponse.setCreatedDate(rs.getString("created_date"));
				srListResponse.setCreatedBy(rs.getString("created_by"));
				srListResponse.setModifiedBy(rs.getString("modified_by"));
				srListResponse.setModifiedDate(rs.getString("modified_date"));
				srListResponse.setAssigneeId(rs.getString("assignee_id"));
				srListResponse.setAsigneeName(rs.getString("asignee_name"));
				srListResponse.setAssigneeUsertype(rs.getString("assignee_usertype"));
		return srListResponse;
	}
	

}
