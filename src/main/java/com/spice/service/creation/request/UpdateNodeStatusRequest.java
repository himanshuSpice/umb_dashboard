package com.spice.service.creation.request;

import lombok.Data;

@Data
public class UpdateNodeStatusRequest {
	private String status;
	private String nodeId; 
	private String serviceId;

}
