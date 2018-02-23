package com.spice.service.creation.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class DeleteTreeNodeRequest {
	@NotBlank(message="105")
	@NotNull(message="105")
	@Pattern(regexp="([0-9]+)?",message="103")
	private String nodeId;
	private  String serviceId;
}
