package com.spice.service.creation.request;

import lombok.Data;

@Data
public class UpdateServiceDetailRequest {
	private String serviceId;
	private String serviceName;
	private String status;
	private String description;
	
}
