package com.spice.service.creation.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	
	
	private String userId;
	private String password;
}
