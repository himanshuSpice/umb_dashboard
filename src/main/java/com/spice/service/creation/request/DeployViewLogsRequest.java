package com.spice.service.creation.request;

import lombok.Data;

@Data
public class DeployViewLogsRequest {
	private String deploymentType;
	private String fromDate;
	private String toDate;
}
