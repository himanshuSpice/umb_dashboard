package com.spice.service.creation.request;

import lombok.Data;

@Data
public class CreateServiceMasterRequest {
	
	private String creationMode;
	private String sourceServiceId;
	private String parentServiceId;
	private String serviceName;
	private String serviceCode;
	private String description;
	private String treeType;
}
