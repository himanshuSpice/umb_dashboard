package com.spice.service.creation.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotNull(message="105")
	@NotBlank(message="105")
	private String userName;
	
	@NotNull(message="105")
	@NotBlank(message="105")
	private String password;

}
