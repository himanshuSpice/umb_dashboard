package com.spice.service.creation.response;

import lombok.Data;

@Data
public class ResponseObj {
	private int statusCode;
	private String description;
	private String status;
	private Object object;
	
	
	public ResponseObj(Object object, String status, String description,int statusCode) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.description = description;
		this.object = object;
	}
	
	public ResponseObj(Object object) {
		super();
		this.statusCode = 100;
		this.status = "Success";
		this.description = "Success";
		this.object = object;
	}
}
