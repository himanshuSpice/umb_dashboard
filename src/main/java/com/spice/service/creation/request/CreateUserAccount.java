package com.spice.service.creation.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserAccount {
	@Pattern(regexp="([0-9]+)?",message="103")
	private String createdById;
	
	@NotBlank(message="105")
	@NotNull(message="105")
	@Length(max=100,message="103")
	private String userName;
	
	@NotBlank(message="105")
	@NotNull(message="105")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])([A-Z]*)(?=.*[@#$%!$^[*]]).{6,20})",message="127")
	private String password;

	@Length(max=100,message="103")
	@Pattern(regexp = "([a-zA-Z][a-zA-Z0-9_-[.]]*@([a-zA-Z]*.[a-zA-Z]*)*)?", message="103")
	private String email;

	private String msisdn;
	private String userIp; 
	@Pattern(regexp="(0|1)?",message="103")
	private String userMngtPermission;
	@Pattern(regexp="(0|1)?",message="103")
	private String serviceMngtPermission;
}
