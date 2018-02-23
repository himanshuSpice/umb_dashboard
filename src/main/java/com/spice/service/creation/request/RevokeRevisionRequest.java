package com.spice.service.creation.request;

import lombok.Data;

@Data
public class RevokeRevisionRequest {
	private String serviceId;
	private String versionMajor;
	private String versionMinor;
	private String remarks;
	

}
