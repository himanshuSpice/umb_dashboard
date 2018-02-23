package com.spice.service.creation.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FetchServiceDetailResponse {
	private String service_id;
	private String service_name;
	private String service_code;
	private String creator_id;
	private String parent_service_id;
	private String scope;
	private String dcs;
	private String circle;
	private String status;
	private String operator;
	private String subscriber_type;
	private String description;
	private String created_on;
	private String last_modify_by;
	private String last_modified;
	

}
