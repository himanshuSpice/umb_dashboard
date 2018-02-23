package com.spice.service.creation.request;

import lombok.Data;

@Data
public class SrCreateServicRequest {
	private String srId;
	private String name;
	private String desc;
	private String title;
	private String assignee;
	private String targetDate;
	private String inStatus;
}
