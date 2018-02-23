package com.spice.service.creation.request;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ServiceStatus {
	@Pattern(regexp="([0-9]+)?",message="103")
	private String serviceId;
	@Pattern(regexp="(0|1|3|4)?",message="103")
	private String status;
 
}
