package com.spice.service.creation.response;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class SingleServiceNodeReponse implements RowMapper<SingleServiceNodeReponse> {
	private String nodeId;
	private String parentId;
	private String status;
	private String serviceId;
	private String serviceCode;
	private String requestText;
	private String responseText;
	private String regex;
	private String invalidResponseText;
	private String invalidMenuFlag;
	private String nodeType;
	private String isHeader;
	private String isFooter	;
	private String isListElement;
	private String preference;
	private String menuFlag;
	private String dcs;
	private String applicationId;
	private String variableName;
	private String variableValue;
	private String switchServiceId;
	private String switchServiceCode;
	private String circleId;
	private String operatorId;
	private String ifClause;
	private String ifValueClause;
	private String createdOn;
	private String lastModified;
	public SingleServiceNodeReponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		SingleServiceNodeReponse response = new SingleServiceNodeReponse();
		response.setNodeId(rs.getString("node_id"));
		response.setParentId(rs.getString("parent_id"));
		response.setStatus(rs.getString("STATUS"));
		response.setServiceId(rs.getString("service_id"));
		response.setServiceCode(rs.getString("service_code"));
		response.setRequestText(rs.getString("request_text"));
		response.setResponseText(rs.getString("response_text"));
		response.setRegex(rs.getString("regex"));
		response.setInvalidResponseText(rs.getString("invalid_response_text"));
		response.setInvalidMenuFlag(rs.getString("invalid_menu_flag"));
		response.setNodeType(rs.getString("node_type"));
		response.setIsHeader(rs.getString("is_header"));
		response.setIsFooter(rs.getString("is_footer"));
		response.setIsListElement(rs.getString("is_list_element"));
		response.setPreference(rs.getString("preference"));
		response.setMenuFlag(rs.getString("menu_flag"));
		response.setDcs(rs.getString("dcs"));
		response.setApplicationId(rs.getString("application_id"));
		response.setVariableName(rs.getString("variable_name"));
		response.setVariableValue(rs.getString("variable_value"));
		response.setSwitchServiceId(rs.getString("switch_service_id"));
		response.setSwitchServiceCode(rs.getString("switch_service_code"));
		response.setCircleId(rs.getString("circle_id"));
		response.setOperatorId(rs.getString("operator_id"));
		response.setIfClause(rs.getString("if_clause"));
		response.setIfValueClause(rs.getString("if_value_clause"));
		response.setCreatedOn(rs.getString("created_on"));
		response.setLastModified(rs.getString("last_modified"));
		return response;
	}

}
