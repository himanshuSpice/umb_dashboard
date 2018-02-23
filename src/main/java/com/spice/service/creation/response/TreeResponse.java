package com.spice.service.creation.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class TreeResponse {
	private String id;
	private String name;
	private String editable;
	private String switch_service_id;
	private String copy;
	private String delete;
	private String change_status;
	private String status;
	@JsonInclude(Include.NON_EMPTY)
	private List<TreeResponse> children;

}
