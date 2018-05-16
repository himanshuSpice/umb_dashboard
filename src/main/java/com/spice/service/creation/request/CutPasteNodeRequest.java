package com.spice.service.creation.request;

import lombok.Data;

@Data
public class CutPasteNodeRequest {
	private String sourceServiceId;
	private String sourceNodeId;
	private String destServiceId;
	private String destNodeId;
	

}
