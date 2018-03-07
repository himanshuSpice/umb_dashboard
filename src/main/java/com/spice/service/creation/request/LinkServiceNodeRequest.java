package com.spice.service.creation.request;

import lombok.Data;

@Data
public class LinkServiceNodeRequest {
	private String serviceId;
	private String sourceNodeId;
	private String destNodeId;

}
