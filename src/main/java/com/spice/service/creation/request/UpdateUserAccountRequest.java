package com.spice.service.creation.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UpdateUserAccountRequest {

	@Pattern(regexp="([0-9]+)?",message="103")
	private String userId;
	
	private String userName;
	
	private String userType;

	@Length(max=100,message="103")
	@Pattern(regexp = "([a-zA-Z][a-zA-Z0-9_-[.]]*@([a-zA-Z]*.[a-zA-Z]*)*)?", message="103")
	private String email;
	
	private String password;
	
	private String msisdn;
	@Pattern(regexp="(0|1)?",message="103")
	private String userMngtPermission;
	@Pattern(regexp="(0|1)?",message="103")
	private String serviceMngtPermission;
	
}
