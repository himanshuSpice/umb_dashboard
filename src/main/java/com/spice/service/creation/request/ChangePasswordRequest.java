package com.spice.service.creation.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class ChangePasswordRequest {
	
	@NotNull(message="105")
	@NotBlank(message="105")
	@Length(max=100,message="103")
	private String userId;
	private String password;
	
	@NotNull(message="105")
	@NotBlank(message="105")
	private String oldPassword;
	

}
