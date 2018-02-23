package com.spice.service.creation.request;

import lombok.Data;

@Data
public class AddMsisdnRequest {
	private String serviceId;
	private String msisdn;

}
