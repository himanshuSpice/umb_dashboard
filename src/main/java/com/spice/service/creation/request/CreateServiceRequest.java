package com.spice.service.creation.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateServiceRequest {
	private String serviceName;
	
	@NotBlank(message="105")
	@NotNull(message="105")
	@Length(max=40,message="103")
	@Pattern(regexp="(([0-9]+)|([a-zA-Z]+)|(?=.*\\d)?[(a-zA-Z)*]?(?=.*[_]?).{3,50})?",message="103")
	private String serviceCode;
	private String creatorId;
	private String parentServiceId;
	private String scope;
	private String dcs;
	private String circle;
	private String status;
	
	@Pattern(regexp = "([0-9]+)?", message="103")
	private String operator;
	private String subscriberType;
	private String description;

}
